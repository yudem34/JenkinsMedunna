@US28
Feature: US_28 Ulke Olusturma ve Silme

  @SmokeTest
  Scenario: Admin ulke olusturabilmeli ve Silebilmeli
    Given Admin www.medunna.com adresine gider
     When Giris yap simgesine ve acilan menude Sign-in secenegine tiklar
      And Admin gecerli bir Username girer
      And Admin gecerli bir password girer
      And Sign in butonuna tiklar
      And items&titles bolumu acilir
      And Country secenegine tiklar
      And Create a new Country butonuna tiklar
     Then Name kismina ulke adini girer
      And Country-save butonuna  tiklar
      And Ulke kaydinin basarili sekilde yapildigini dogrular
      And Admin ulke silebilmeli
     Then Silmek istedigi ulkenin Delete butonuna tiklar
      And Delete butonuna basma istegini confirm eder
     Then Ulkenin basariyla silindigini dogrular
     Then Sayfayi kapatir





