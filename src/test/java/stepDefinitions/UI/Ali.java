package stepDefinitions.UI;

import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import pages.LoginPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

import java.util.ArrayList;
import java.util.List;


public class Ali {

    Actions actions = new Actions(Driver.getDriver());
    JavascriptExecutor js = ((JavascriptExecutor) Driver.getDriver());
    String gunelUrun="Serum Yeni";
    LoginPage medunna = new LoginPage();
    pages.Ali homepage=new pages.Ali();
    Faker faker=new Faker();
    static String country="YeniCountryTeam21Project";

    @And("items&titles bolumu acilir")
    public void itemsTitlesBolumuAcilir() throws InterruptedException {
        homepage.itemsTitles.click();
        Thread.sleep(2000);
    }

    @And("Messages bolumune girilir")
    public void messagesBolumuneGirilir() {
        homepage.messagesButton.click();
    }

    @And("ID-Name-Email-Subject-Message bolumlerinin gorunebilirligi test edilir")
    public void idNameEmailSubjectMessageBolumlerininGorunebilirligiTestEdilir() {

        Assert.assertTrue(homepage.messagesID.isDisplayed());
        Assert.assertTrue(homepage.messagesName.isDisplayed());
        Assert.assertTrue(homepage.messagesEmail.isDisplayed());
        Assert.assertTrue(homepage.messagesSubject.isDisplayed());
        Assert.assertTrue(homepage.messagesMessage.isDisplayed());


    }

    @And("Mesaj olusturma bolumune girilir")
    public void mesajOlusturmaBolumuneGirilir() {
        homepage.messagesNewMessage.click();
    }

    @And("Name-Email-Subject-Message bolumleri doldurulur")
    public void nameEmailSubjectMessageBolumleriDoldurulur() throws InterruptedException {
        homepage.messagesNewName.sendKeys(faker.name().fullName());
        Thread.sleep(1000);
        homepage.messagesNewEmail.sendKeys(faker.internet().emailAddress());
        Thread.sleep(1000);
        homepage.messagesNewSubject.sendKeys(faker.superhero().name(),Keys.TAB);
        Thread.sleep(1000);
        actions.sendKeys(ConfigReader.getProperty("fakeMessage")).perform();
    }


    @And("Save butonu ile mesaj kaydi denenir")
    public void saveButonuIleMesajKaydiDenenir() {
        homepage.messagesSaveMessage.click();
    }

    @Then("Mesajin olusmus olmasi denenir")
    public void mesajinOlusmusOlmasiDenenir() {
        homepage.messagesID.click();
        Assert.assertEquals(ConfigReader.getProperty("fakeMessage"),homepage.lastSentMessages.getText());


    }

    @Then("Guncellenecek-silinecek mesajin ID'sine tiklanir")
    public void guncellenecekSilinecekMesajinIDSineTiklanir() {
        homepage.ilkMessageID.click();

    }


    @And("Edit butonu ile duzenleme menusu acilir")
    public void editButonuIleDuzenlemeMenusuAcilir() {
        homepage.editMessage.click();
    }

    @And("Degisiklik-duzenleme yapilir")
    public void degisiklikDuzenlemeYapilir() {
        homepage.editMessageWrite.sendKeys(ConfigReader.getProperty("fakeMessage"));

    }

    @Then("Message edit save butonuna tiklanir")
    public void messageEditSaveButonunaTiklanir() {
        homepage.editMessageSave.click();
    }

    @And("Mesaj duzenlemesi denenir")
    public void mesajDuzenlemesiDenenir() {
        homepage.messagesID.click();
        Assert.assertEquals(ConfigReader.getProperty("fakeMessage"),homepage.lastSentMessages.getText());

    }

    @And("Country secenegine tiklar")
    public void countrySecenegineTiklar() throws InterruptedException {
        homepage.countryButton.click();
    }

    @Then("Silmek istedigi ulkenin Delete butonuna tiklar")
    public void silmekIstedigiUlkeninDeleteButonunaTiklar() throws InterruptedException {
        List<String> countries=new ArrayList<>();
        for (WebElement each:homepage.countryList) {
            countries.add(each.getText());
        }
        Thread.sleep(2000);
        for (int i = 0; i <countries.size() ; i++) {
            if (countries.get(i).equals(country)){

                js.executeScript("arguments[0].scrollIntoView(true);", Driver.getDriver().findElement(By.xpath("(//*[text()='Delete'])" + "[" + (i + 1) + "]")));
                js.executeScript("arguments[0].click();", Driver.getDriver().findElement(By.xpath("(//*[text()='Delete'])" + "[" + (i + 1) + "]")));

            }
        }
        Thread.sleep(2000);

    }

    @And("Create a new Country butonuna tiklar")
    public void createANewCountryButonunaTiklar() throws InterruptedException {
        homepage.newCountryButton.click();
        Thread.sleep(1000);
    }

    @Then("Name kismina ulke adini girer")
    public void nameKisminaUlkeAdiniGirer() throws InterruptedException {
        homepage.newCountryNameButton.sendKeys(country);
        Thread.sleep(1000);
    }

    @And("Country-save butonuna  tiklar")
    public void countrySaveButonunaTiklar() throws InterruptedException {
        homepage.countrySaveButton.click();
        Thread.sleep(1000);
    }

    @And("Ulke kaydinin basarili sekilde yapildigini dogrular")
    public void ulkeKaydininBasariliSekildeYapildiginiDogrular() throws InterruptedException {
        Assert.assertTrue(homepage.onayYazisi.isDisplayed());
        Thread.sleep(1000);
    }

    @And("Delete butonuna basma istegini confirm eder")
    public void deleteButonunaBasmaIsteginiConfirmEder() throws InterruptedException {
        Thread.sleep(1000);
        actions.sendKeys(Keys.TAB)
                .sendKeys(Keys.TAB)
                .sendKeys(Keys.TAB)
                .sendKeys(Keys.ENTER).perform();
        Thread.sleep(2000);
    }

    @Then("Ulkenin basariyla silindigini dogrular")
    public void UlkeninBasariylaSilindiginiDogrular() throws InterruptedException {
        Assert.assertTrue(homepage.onayYazisi.isDisplayed());
        Thread.sleep(2000);
    }

    @When("{int} saniye bekleme")
    public void saniyeBekleme(int millis) throws InterruptedException {
        Thread.sleep(millis*2000);
    }

    @And("Admin ulke silebilmeli")
    public void adminUlkeSilebilmeli() throws InterruptedException {
        Thread.sleep(1000);
    }
}


