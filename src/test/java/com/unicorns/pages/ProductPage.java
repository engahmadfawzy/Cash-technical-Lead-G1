package com.unicorns.pages;

import com.unicorns.driver.WebDriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class ProductPage extends BasePage {
    private By productPriceField = By.cssSelector("div.product-details-price");
    private By productNameField= By.cssSelector("div.product-content.text-center h3 a");
    private By addToCartField = By.cssSelector("div.pro-details-cart.btn-hover button");
    private By noItemsTitle   =By.xpath("//p[text()='No items added to cart']");
    private By cartIcon =By.cssSelector(".icon-cart");
    private By itemAddedToCart =By.cssSelector(".shopping-cart-btn.btn-hover.text-center");
    private By productsButton =By.xpath("(//li//a[text()='Products'])[1]");
    private By firstItemPriceLocator =By.xpath("(//span[preceding::h6[1]])[1]");
    private By secondItemPriceLocator =By.xpath("(//span[preceding::h6[2]])[1]");
    private By totalItemsPriceLocator =By.cssSelector("span.shop-total");
    private By checkoutButton =By.cssSelector("div.shopping-cart-btn.btn-hover.text-center a[href='/checkout']");
    private By addedToCartMsg = By.xpath("//*[.,'Added To Cart']");

    public Boolean areProductNameAndPriceShown(){
        return(findElement(productPriceField).isDisplayed()&&findElement(productNameField).isDisplayed());
    }

    public void clickOnAddToCartButton(){
        findElement(addToCartField).click();
    }

    public Boolean checkIfTheCartIsEmpty(){
        findElement(cartIcon).click();
        return (findElement(noItemsTitle).isDisplayed());
    }

    public void clickOnCartButton(){
        findElement(cartIcon).click();
    }

    public Boolean checkItemsAddedToCart(){
        findElement(cartIcon).click();
        return (findElement(itemAddedToCart).isDisplayed());
    }

    public void clickOnProductsButton() {
        WebDriverWait wait = new WebDriverWait(WebDriverSingleton.getWebDriver(), Duration.ofSeconds(waitTime));
        wait.until(ExpectedConditions.invisibilityOf(findElement(addedToCartMsg)));
        findElement(productsButton).click();
    }

    public double getFirstItemPrice() {
        String price1 = findElement(firstItemPriceLocator).getText().replaceAll("[^\\d.]", "");
        return Double.parseDouble(price1);
    }
    public double getSecondItemPrice() {
        String price2 = findElement(secondItemPriceLocator).getText().replaceAll("[^\\d.]", "");
        return Double.parseDouble(price2);
    }
    public double getTotalItemsPrice() {
        String priceTotal = findElement(totalItemsPriceLocator).getText().replaceAll("[^\\d.]", "");
        return Double.parseDouble(priceTotal);
    }

    public void clickOnCheckoutButton(){
      findElement(checkoutButton).click();
    }

    }
