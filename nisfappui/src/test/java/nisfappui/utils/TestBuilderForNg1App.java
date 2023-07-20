package nisfappui.utils;

import nisfappui.pages.MainSFAppPage;
import nisfappui.pages.MerchantInitialCreationPage;
import nisfappui.pages.NavigationMenuPartitions;

import static nisfappui.services.ApplicationProductTypes.*;
import static nisfappui.services.Ng1AuthSystemTypes.BASE24;
import static nisfappui.utils.AppDataFaker.getRandomEmail;
import static nisfappui.utils.AppDataFaker.getRandomTradeName;

public class TestBuilderForNg1App {

    public void openNewMerchantPopUp(MainSFAppPage mainSFAppPage, NavigationMenuPartitions navigationMenuPartitions) {
        mainSFAppPage
                .clickOnNavigationMenuType()
                .chooseOnNavigationMenuType(navigationMenuPartitions)
                .clickOnNewAppBtn();
    }


    public void fillMerchantInitialCreationPagePopUp(MerchantInitialCreationPage merchantInitialCreationPage,
                                                     String bnt) {
        merchantInitialCreationPage
                .fillTradeName(getRandomTradeName())
                .fillMerchantEmail(getRandomEmail())
                .chooseBusinessNatureType(bnt)
                .unselectSelectedProduct(POS.getDisplayName())
                .unselectSelectedProduct(ECOM.getDisplayName())
                .selectAvailableProduct(NG1.getDisplayName())
                .selectNg1ECOMAuthType(BASE24.getDisplayName())
                .selectNg1SoftPOSAuthType(BASE24.getDisplayName())
                .moveToTheSecondApplicationScreen();
    }
}
