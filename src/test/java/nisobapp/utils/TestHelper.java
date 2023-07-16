package nisobapp.utils;

public class TestHelper {

    public static final String SOB_CREATION_URL = "https://network-international--uat.sandbox.my.salesforce.com/services/apexrest/selfonboarding/application";
    public static final String TOKEN_URL = "https://test.salesforce.com/services/oauth2/token";
    public static final String APP_ID_REGEX = "A-\\d{9,}";

    public static final String SCREENING_RESULT = "  { \"entities\" : [ { \"entityId\" : \"%s\",    \"screeningResult\" : {      " +
            "\"FISERV\" : { \"entityId\" : \"%s\",  \"result\" : \"GO\", \"matchedMerchants\" : null},\"MATCH\" : {\"entityId\" : \"%s\", " +
            "\"result\" : \"GO\",  \"matchedMerchants\" : null }}},{   \"entityId\" : \"%s\",    \"screeningResult\" : {      " +
            "\"FISERV\" : { \"entityId\" : \"%s\",  \"result\" : \"GO\", \"matchedMerchants\" : null},\"MATCH\" : {       " +
            "\"entityId\" : \"%s\",       \"result\" : \"GO\",       \"matchedMerchants\" : null     }   } },{   " +
            "\"entityId\" : \"%s\",    \"screeningResult\" : {      \"FISERV\" : { \"entityId\" : \"%s\",  " +
            "\"result\" : \"GO\", \"matchedMerchants\" : null},\"MATCH\" : {       \"entityId\" : \"%s\",       " +
            "\"result\" : \"GO\",       \"matchedMerchants\" : null     }   } }, {   \"entityId\" : \"%s\",    " +
            "\"screeningResult\" : {      \"FISERV\" : { \"entityId\" : \"%s\",  \"result\" : \"GO\", \"matchedMerchants\" : null}}}, " +
            "{   \"entityId\" : \"%s\",    \"screeningResult\" : {      \"FISERV\" : { \"entityId\" : \"%s\",  " +
            "\"result\" : \"GO\", \"matchedMerchants\" : null}}}]}";

    public static final String IBAN = "AE540035951185368235123";
    public static final String IBAN_ACC_NUM = "368235123";
    public static final String DOC_ID_1 = "bfbdbce5-a757-4296-90d9-eea950e187d1721d894f-d36f-4593-ade7-49ff4dc82e25";
    public static final String DOC_ID_1_1 = "cff27aaf-f1ad-4baf-814e-f84e6b4cb5cb30c365a9-8fd9-4863-8232-a04dcd67912d";
    public static final String DOC_ID_2 = "bfbdbce5-a757-4296-90d9-eea950e187d1b3003076-a868-4782-9502-07c1e8c38c32";
    public static final String DOC_ID_2_2 = "cff27aaf-f1ad-4baf-814e-f84e6b4cb5cb02afb6e7-a117-4a78-aba1-9853bcfbea08";
    public static final String DOC_ID_3 = "bfbdbce5-a757-4296-90d9-eea950e187d1ab705500-f13e-46a7-899e-dcfa48c643bd";
    public static final String DOC_ID_3_3 = "cff27aaf-f1ad-4baf-814e-f84e6b4cb5cb746f002d-d3b0-4b1b-ac33-3d30de26b2db";
    public static final String DOC_ID_4 = "bfbdbce5-a757-4296-90d9-eea950e187d1543a8a3d-c46e-4638-b55c-0f114a4cdd06";
    public static final String DOC_ID_4_4 = "cff27aaf-f1ad-4baf-814e-f84e6b4cb5cbb6ea2ded-d835-4e76-976d-62139957a5bb";
    public static final String DOC_ID_5 = "bfbdbce5-a757-4296-90d9-eea950e187d1e443d977-4c08-46b1-8483-24506a6ad826";
    public static final String DOC_ID_5_5 = "cff27aaf-f1ad-4baf-814e-f84e6b4cb5cbc5018c45-a384-472e-974d-3bef15edf025";
    public static final String DOC_ID_6 = "bfbdbce5-a757-4296-90d9-eea950e187d14986d3e8-d652-46be-87bc-dbfac06c1c66";
    public static final String DOC_ID_6_6 = "cff27aaf-f1ad-4baf-814e-f84e6b4cb5cbd2bd6142-1a86-4c6b-8560-4f8c127af722";
    public static final String DOC_ID_7 = "bfbdbce5-a757-4296-90d9-eea950e187d16025e985-52b0-4739-b870-e6fcfbdb0b58";
    public static final String DOC_ID_7_7 = "cff27aaf-f1ad-4baf-814e-f84e6b4cb5cb56137534-d7de-4657-a175-556e2b68da5f";
    public static final String DOC_ID_8 = "bfbdbce5-a757-4296-90d9-eea950e187d174b6242a-fb34-42ca-a2d9-70ffe2fd425b";
    public static final String DOC_ID_8_8 = "cff27aaf-f1ad-4baf-814e-f84e6b4cb5cbe8f00695-070e-4e84-beed-f81561328f0c";
    public static final String DOC_ID_9 = "bfbdbce5-a757-4296-90d9-eea950e187d1d2caa2d8-9555-4e2b-84fb-e7d1e9554335";
    public static final String DOC_ID_9_9 = "cff27aaf-f1ad-4baf-814e-f84e6b4cb5cb74f32add-44aa-405a-9d55-35f87cb37684";
    public static final String DOC_ID_10_10 = "cff27aaf-f1ad-4baf-814e-f84e6b4cb5cb3eeac372-9075-4e0c-9ba5-34cc2d863b13";
    public static final String DOC_ID_11_11 = "cff27aaf-f1ad-4baf-814e-f84e6b4cb5cb4e83eae7-887e-45b8-b038-06333842378b";
    public static final String DOC_ID_12_12 = "cff27aaf-f1ad-4baf-814e-f84e6b4cb5cb4cba2d6d-6cbf-4b5b-9a9f-ca2dcaf886cc";
    public static final String DOC_ID_13_13 = "cff27aaf-f1ad-4baf-814e-f84e6b4cb5cb9d1b0f9f-20b7-45d8-8976-3b137f05ee72";
    public static final String DOC_ID_14_14 = "cff27aaf-f1ad-4baf-814e-f84e6b4cb5cb921f559b-904a-4622-8a19-40b35e80be7e";
    public static final String DOC_URL_1 = "https://onboarding-uat.network.ae/files/shared/sob-ni-1876/bfbdbce5-a757-4296-90d9-eea950e187d1721d894f-d36f-4593-ade7-49ff4dc82e25?accessKey=b347409902a86defc10021ff96a1c8c4cac7c6bffce81c8a468a3dab480e3c74";
    public static final String DOC_URL_1_1 = "https://onboarding-uat.network.ae/files/shared/self-onboarding-665-container/cff27aaf-f1ad-4baf-814e-f84e6b4cb5cb30c365a9-8fd9-4863-8232-a04dcd67912d?accessKey=0076aeca492b42150e45a0640682c495f0254386f4ae8d4f17ea5f36156169ac";
    public static final String DOC_URL_2 = "https://onboarding-uat.network.ae/files/shared/sob-ni-1876/bfbdbce5-a757-4296-90d9-eea950e187d1b3003076-a868-4782-9502-07c1e8c38c32?accessKey=ea66de173c685b9bc627fef8a931ae5192ee9ee10822b580836632851a8b434a";
    public static final String DOC_URL_2_2 = "https://onboarding-uat.network.ae/files/shared/self-onboarding-665-container/cff27aaf-f1ad-4baf-814e-f84e6b4cb5cb02afb6e7-a117-4a78-aba1-9853bcfbea08?accessKey=98b352e0470a8d74ba2d9f8ce02f61a5849d24ffacce9a135d508d9a12b7fed7";
    public static final String DOC_URL_3 = "https://onboarding-uat.network.ae/files/shared/sob-ni-1876/bfbdbce5-a757-4296-90d9-eea950e187d1ab705500-f13e-46a7-899e-dcfa48c643bd?accessKey=83bcd0944c1ca0dd22cdac0fce2a05fb97734e0aaa53072d9d8ae0dfee44aa48";
    public static final String DOC_URL_3_3 = "https://onboarding-uat.network.ae/files/shared/self-onboarding-665-container/cff27aaf-f1ad-4baf-814e-f84e6b4cb5cb746f002d-d3b0-4b1b-ac33-3d30de26b2db?accessKey=d2771cf4f74749a8f6cad30505f2eb60a56747a1fc6f861446722f1b1f650e91";
    public static final String DOC_URL_4 = "https://onboarding-uat.network.ae/files/shared/sob-ni-1876/bfbdbce5-a757-4296-90d9-eea950e187d1543a8a3d-c46e-4638-b55c-0f114a4cdd06?accessKey=462cf6a8b98dbd808e52b6f5d8d51db3bc16e27a55f9030f6c95f4c8739c6ab9";
    public static final String DOC_URL_4_4 = "https://onboarding-uat.network.ae/files/shared/self-onboarding-665-container/cff27aaf-f1ad-4baf-814e-f84e6b4cb5cbb6ea2ded-d835-4e76-976d-62139957a5bb?accessKey=44437fb08a0572bd288daca56f58da5ab9a44ab2352a12d532d5395f060017f4";
    public static final String DOC_URL_5 = "https://onboarding-uat.network.ae/files/shared/sob-ni-1876/bfbdbce5-a757-4296-90d9-eea950e187d1e443d977-4c08-46b1-8483-24506a6ad826?accessKey=777ac34cd34ade4838cf6264c9ffbbaf1b063fd40859d5ccdd5b17945ba13626";
    public static final String DOC_URL_5_5 = "https://onboarding-uat.network.ae/files/shared/self-onboarding-665-container/cff27aaf-f1ad-4baf-814e-f84e6b4cb5cbc5018c45-a384-472e-974d-3bef15edf025?accessKey=2db21815807a7d4157d0783c0877d4b0616ba1567fa3c28cb8cbd1224d082928";
    public static final String DOC_URL_6 = "https://onboarding-uat.network.ae/files/shared/sob-ni-1876/bfbdbce5-a757-4296-90d9-eea950e187d14986d3e8-d652-46be-87bc-dbfac06c1c66?accessKey=5949eb7645f5e1a57b1c21f74097ce1d0d6f2036e08a06b822ff27ba383ce426";
    public static final String DOC_URL_6_6 = "https://onboarding-uat.network.ae/files/shared/self-onboarding-665-container/cff27aaf-f1ad-4baf-814e-f84e6b4cb5cbd2bd6142-1a86-4c6b-8560-4f8c127af722?accessKey=9ce9ab94c3c71f2779ae4a39a72d2eeb510baa9a5e1701180c1b9bac5eabf588";
    public static final String DOC_URL_7 = "https://onboarding-uat.network.ae/files/shared/sob-ni-1876/bfbdbce5-a757-4296-90d9-eea950e187d16025e985-52b0-4739-b870-e6fcfbdb0b58?accessKey=67e36b31e81995646605d192ea83048dc120846c3883279ab311c8ed18c21d47";
    public static final String DOC_URL_7_7 = "https://onboarding-uat.network.ae/files/shared/self-onboarding-665-container/cff27aaf-f1ad-4baf-814e-f84e6b4cb5cb56137534-d7de-4657-a175-556e2b68da5f?accessKey=b4da6421120b7b9581c57954b94a663c0268142ea1acdfd5007a34cb5b4ac1ae";
    public static final String DOC_URL_8 = "https://onboarding-uat.network.ae/files/shared/sob-ni-1876/bfbdbce5-a757-4296-90d9-eea950e187d174b6242a-fb34-42ca-a2d9-70ffe2fd425b?accessKey=410af5360e53b6ddf85c487ed0ccabf1a5d668af21391703e1454ebc84f67e18";
    public static final String DOC_URL_8_8 = "https://onboarding-uat.network.ae/files/shared/self-onboarding-665-container/cff27aaf-f1ad-4baf-814e-f84e6b4cb5cbe8f00695-070e-4e84-beed-f81561328f0c?accessKey=f526982d5688c288cf8c92cc6fd2a10fc1883572b63722f88d7be824fa9cf589";
    public static final String DOC_URL_9 = "https://onboarding-uat.network.ae/files/shared/sob-ni-1876/bfbdbce5-a757-4296-90d9-eea950e187d1d2caa2d8-9555-4e2b-84fb-e7d1e9554335?accessKey=c5bdbbba6e27df124f1d3c9a498c558f7a24579b2230d774e07706862133bd32";
    public static final String DOC_URL_9_9 = "https://onboarding-uat.network.ae/files/shared/self-onboarding-665-container/cff27aaf-f1ad-4baf-814e-f84e6b4cb5cb74f32add-44aa-405a-9d55-35f87cb37684?accessKey=5cdb48a60f766fab8c30215d0f5c033d9ccf9a2740b956dc099c749f87996456";
    public static final String DOC_URL_10_10 = "https://onboarding-uat.network.ae/files/shared/self-onboarding-665-container/cff27aaf-f1ad-4baf-814e-f84e6b4cb5cb3eeac372-9075-4e0c-9ba5-34cc2d863b13?accessKey=9d09c657cf298f6a6069d19fa65343bee3be076a5729fff8be5f72e177e99dcb";
    public static final String DOC_URL_11_11 = "https://onboarding-uat.network.ae/files/shared/self-onboarding-665-container/cff27aaf-f1ad-4baf-814e-f84e6b4cb5cb4e83eae7-887e-45b8-b038-06333842378b?accessKey=f4b0d34567e22f751648d6bc16d81a88a07ad826bc5978519ad867ef46667044";
    public static final String DOC_URL_12_12 = "https://onboarding-uat.network.ae/files/shared/self-onboarding-665-container/cff27aaf-f1ad-4baf-814e-f84e6b4cb5cb4cba2d6d-6cbf-4b5b-9a9f-ca2dcaf886cc?accessKey=5c871b28c8aaa8fb0ffc82fee471a102e65122fe245f8b8be41fb782442a09ef";
    public static final String DOC_URL_13_13 = "https://onboarding-uat.network.ae/files/shared/self-onboarding-665-container/cff27aaf-f1ad-4baf-814e-f84e6b4cb5cb9d1b0f9f-20b7-45d8-8976-3b137f05ee72?accessKey=3092f3ec4c49aad21e46e1d81ad07f8ac1efcdfc2b073e9cc6ea08075c6df162";
    public static final String DOC_URL_14_14 = "https://onboarding-uat.network.ae/files/shared/self-onboarding-665-container/cff27aaf-f1ad-4baf-814e-f84e6b4cb5cb921f559b-904a-4622-8a19-40b35e80be7e?accessKey=bcf4d0cfde010a79be49153e1bef52a40d332da2721b56451631bcc7a80b6fe2";


}
