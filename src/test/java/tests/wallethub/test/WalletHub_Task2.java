package tests.wallethub.test;

import main.MainCalls;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.reporters.jq.Main;
import tests.base.Hooks;
import utils.GlobalVars;

public class WalletHub_Task2 extends Hooks {
    @Test
    public void LoginToWalletHubWebSite() {

        String webUrl = GlobalVars.WALLET_HUB_LOGIN_URL;
        String profileUrl = GlobalVars.WALLET_HUB_PROFILE_URL;
        String username = GlobalVars.WALLET_HUB_USER_NAME;
        String password = GlobalVars.WALLET_HUB_USER_PASSWORD;
        String profile_name = GlobalVars.WALLET_HUB_PROFILE_NAME;

        Boolean isEditProfileVisible = true;
        String ratingHeadingTitle = "What's Your Rating?";
        String startToRate = "4 star rating";
        String insuranceType = "Health Insurance";

        String reviewMessage = "WalletHub is an excellent service for receiving a free credit report and daily." +
                " credit score from TransUnion. It offers educational information about personal," +
                " credit (but not as much editorial content as NerdWallet), and the targeted advertising is useful and relevant.";
        try
        {
            //  LOGIN TO WALLET HUB ACCOUNT
            MainCalls.getWallethub_login_po().navigateTo_URL(webUrl);
            MainCalls.getWallethub_login_po().LoginToWalletHubAccount(username, password);

            //  VERIFY LOGIN TO WALLET HUB ACCOUNT SUCCESSFUL
            MainCalls.getWallethub_login_po().VerifyIsProfileNameVisible(profile_name);
            MainCalls.getWallethub_login_po().VerifyIsEditProfileButtonVisible(isEditProfileVisible);

            //  NAVIGATE TO TEST INSURANCE PROFILE
            profile_name = "Test Insurance Company";
            MainCalls.getWallethub_review_po().navigateTo_URL(profileUrl);
            MainCalls.getWallethub_login_po().VerifyIsProfileNameVisible(profile_name);

            //  CLICK REVIEW SECTION AND CHECK RATING TITLE
            MainCalls.getWallethub_review_po().clickReviewSection();
            MainCalls.getWallethub_review_po().verifyReviewSectionIsVisible(ratingHeadingTitle);

            //  CHECK ALL THE STARS LIGHT UP PROPERLY AND PROFILE AND RATINGS ALSO SHOWING IN DIALOG BOX
            MainCalls.getWallethub_review_po().verifyAllRatingStarsLightUpAndClickOnStart(startToRate);
            MainCalls.getWallethub_review_po().verifyRatingProfileNameIsVisible(profile_name);
            MainCalls.getWallethub_review_po().verifyRatingStarsAlsoLightUpOnReviewDialog(4);

            //  SELECT INSURANCE TYPE, WRITE REVIEW AND SUBMIT
            MainCalls.getWallethub_review_po().submitReview(insuranceType, reviewMessage);

            //  VERIFY REVIEW IS SUBMITTED SUCCESSFULLY!
            MainCalls.getWallethub_review_po().verifyReviewSuccessMessage();
            MainCalls.getWallethub_review_po().verifyReviewMessageAfterSubmit(reviewMessage);
        } catch (Exception ex) {
            System.out.println(ex);
            Assert.fail("Some error occurred while executing scripts");
        }
    }
}
