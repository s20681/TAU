import json
import re
import time

from selenium import webdriver
from selenium.webdriver.chrome.service import Service
# selecting HTML elements by e.g. class
from selenium.webdriver.common.by import By
# enhanced logging, statuses
from selenium.webdriver.common.desired_capabilities import DesiredCapabilities
from webdriver_manager.chrome import ChromeDriverManager

capabilities = DesiredCapabilities.CHROME.copy()
capabilities['goog:loggingPrefs'] = {'performance': 'ALL'}


def get_status(logs):
    for log in logs:
        if log['message']:
            d = json.loads(log['message'])
            try:
                content_type = 'text/html' in d['message']['params']['response']['headers']['content-type']
                response_received = d['message']['method'] == 'Network.responseReceived'
                if content_type and response_received:
                    return d['message']['params']['response']['status']
            except:
                pass


def get_web_element_attribute_names(web_element):
    """Get all attribute names of a web element"""
    # get element html
    html = web_element.get_attribute("outerHTML")
    # find all with regex
    pattern = """([a-z]+-?[a-z]+_?)='?"?"""
    return re.findall(pattern, html)


def loads(title=None):
    logs = driver.get_log('performance')
    assert get_status(logs) == 200, "http status assertion error, site did not return 200 as expected."
    if title:
        assert title in driver.title, "site returned different title than expected."
    time.sleep(2.5)


# close the cookies window
def closeCookies():
    cookie_box = driver.find_element(By.ID, 'cookie_box')
    assert cookie_box.is_displayed(), "cookie box seems to not be displayed as expected"
    cookie_button = driver.find_element(By.CLASS_NAME, 'close-cookie-box')
    cookie_button.click()
    time.sleep(2.5)

    # assert cookie box is now hidden
    assert not cookie_box.is_displayed(), "cookie box seem to be displayed."


# search function
def search(searchWord, higherLowerEquals='higher', expectedPrice=None, checkWord=None):
    driver.get(baseUrl)
    search_input = driver.find_element(By.CLASS_NAME, 'quick-search-autocomplete')
    search_input.send_keys(searchWord)
    search_button = driver.find_element(By.CLASS_NAME, 'h-quick-search-submit')
    search_button.click()
    time.sleep(2.5)

    search_results = driver.find_elements(By.CLASS_NAME, 'cat-product')
    assert len(search_results) > 0, "result list is empty."

    product = search_results[0]
    if checkWord: assert checkWord in product.get_attribute(
        'data-product-name').lower(), "search did not find a product with keyword that you expected"
    if expectedPrice:
        productPrice = float(product.get_attribute('data-product-price'))
        if higherLowerEquals == 'higher':
            assert productPrice > expectedPrice, "the results did not fit your price expectation"
        if higherLowerEquals == 'lower':
            assert productPrice < expectedPrice, "the results did not fit your price expectation"
        if higherLowerEquals == 'equals':
            assert productPrice == expectedPrice, "the results did not fit your price expectation"


def getCartValue():
    cartUrl = baseUrl + 'koszyk/'
    driver.get(cartUrl)
    try:
        parentElement = driver.find_element(By.CLASS_NAME, 'summary-box-price')
        divElement = parentElement.find_element(By.TAG_NAME, 'div')
        cartValue = divElement.find_element(By.TAG_NAME, 'b').get_attribute("innerHTML")
        price, currency = cartValue.replace(',', '.').strip().split()
        return price
    except:
        return 0


# #assert cart empty
def cartEmpty():
    driver.get(baseUrl)
    cartValue = driver.find_element(By.CLASS_NAME, 'small-basket-price').get_attribute("innerHTML")
    price, currency = cartValue.replace(',', '.').strip().split()
    assert float(price) == 0.0, "the cart value is not 0"
    time.sleep(2.5)


def addToBasket():
    addToCartButton = driver.find_element(By.CLASS_NAME, 'pushAddToBasketData')
    addToCartButton.click()
    time.sleep(2.5)
    closeCookies()


def testProductPriceAsExpected(product, lowerHigherEquals, expectedPrice):
    search(product, lowerHigherEquals, expectedPrice)
    productPrice = getCartValue()
    assert productPrice == expectedPrice, "the product price wasnt as expected"
    time.sleep(2.5)


def closeWarranty():
    closeWarrantyButton = driver.find_element(By.CLASS_NAME, 'js_no-warrant-btn')
    driver.execute_script("arguments[0].click();", closeWarrantyButton)
    time.sleep(2.5)


driver = webdriver.Chrome(service=Service(ChromeDriverManager().install()), desired_capabilities=capabilities)

baseUrl = 'https://www.morele.net/'
url = baseUrl
driver.get(url)

'''
scenario 1 - init, close cookies window, search, make sure that search results are returned
 and products found are cohesive with what we expected
'''


def init(title=None):
    loads(title)
    closeCookies()


def scenario1():
    search('i9-12900K', 'higher', 1000.0, 'procesor')
    search('fotel diablo xone', 'lower', 1000.0, 'czarny')
    search('PH-GTX1060-6G', 'equals', 906.0)


'''
scenario 2 - check products prices, check basket before addition, add to basket, close warranty popup
make sure that basket summary value is increased by the expected price
'''


# init
def scenario2():
    driver.get(url)
    assert getCartValue() == 0, "the cart value is not 0"
    testProductPriceAsExpected('PH-GTX1060-6G', 'equals', 906.0)
    testProductPriceAsExpected('MUM54A00', 'equals', 849.0)
    addToBasket()
    closeWarranty()
    assert getCartValue() == 849.0, "the cart value is not as expected"


init("Morele")
# scenario1()
scenario2()

# close down the driver object
driver.quit()
