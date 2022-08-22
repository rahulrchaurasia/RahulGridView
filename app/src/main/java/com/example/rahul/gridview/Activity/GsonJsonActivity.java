package com.example.rahul.gridview.Activity;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.example.rahul.gridview.R;
import com.example.rahul.gridview.core.response.GsonResponse;
import com.example.rahul.gridview.core.response.MasterDataBean;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class GsonJsonActivity extends BaseActivity implements  View.OnClickListener{

    Button btnJson ,btnGson;

    String test = "{\n" +
            "  \"Message\": \"Sucess\",\n" +
            "  \"Status\": \"success\",\n" +
            "  \"StatusNo\": 0,\n" +
            "  \"MasterData\": {\n" +
            "    \"DeathBenefit\": [\n" +
            "      {\n" +
            "        \"Year\": \"30\",\n" +
            "        \"JeevanPremiumPaid\": \"283106\",\n" +
            "        \"JeevanBenefitsPayable\": \"3970000\",\n" +
            "        \"LakshyaPremiumPaid\": \"287573\",\n" +
            "        \"LakshyaBenefitsPayable\": \"20970000\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"Year\": \"31\",\n" +
            "        \"JeevanPremiumPaid\": \"566212\",\n" +
            "        \"JeevanBenefitsPayable\": \"3870000\",\n" +
            "        \"LakshyaPremiumPaid\": \"575146\",\n" +
            "        \"LakshyaBenefitsPayable\": \"20870000\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"Year\": \"32\",\n" +
            "        \"JeevanPremiumPaid\": \"843223\",\n" +
            "        \"JeevanBenefitsPayable\": \"3770000\",\n" +
            "        \"LakshyaPremiumPaid\": \"862719\",\n" +
            "        \"LakshyaBenefitsPayable\": \"20770000\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"Year\": \"33\",\n" +
            "        \"JeevanPremiumPaid\": \"1120234\",\n" +
            "        \"JeevanBenefitsPayable\": \"3670000\",\n" +
            "        \"LakshyaPremiumPaid\": \"1150292\",\n" +
            "        \"LakshyaBenefitsPayable\": \"20670000\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"Year\": \"34\",\n" +
            "        \"JeevanPremiumPaid\": \"1397245\",\n" +
            "        \"JeevanBenefitsPayable\": \"3570000\",\n" +
            "        \"LakshyaPremiumPaid\": \"1437865\",\n" +
            "        \"LakshyaBenefitsPayable\": \"20570000\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"Year\": \"35\",\n" +
            "        \"JeevanPremiumPaid\": \"1674256\",\n" +
            "        \"JeevanBenefitsPayable\": \"3470000\",\n" +
            "        \"LakshyaPremiumPaid\": \"1725438\",\n" +
            "        \"LakshyaBenefitsPayable\": \"20470000\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"Year\": \"36\",\n" +
            "        \"JeevanPremiumPaid\": \"1951267\",\n" +
            "        \"JeevanBenefitsPayable\": \"3370000\",\n" +
            "        \"LakshyaPremiumPaid\": \"2013011\",\n" +
            "        \"LakshyaBenefitsPayable\": \"20370000\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"Year\": \"37\",\n" +
            "        \"JeevanPremiumPaid\": \"2228278\",\n" +
            "        \"JeevanBenefitsPayable\": \"3270000\",\n" +
            "        \"LakshyaPremiumPaid\": \"2300584\",\n" +
            "        \"LakshyaBenefitsPayable\": \"20270000\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"Year\": \"38\",\n" +
            "        \"JeevanPremiumPaid\": \"2505289\",\n" +
            "        \"JeevanBenefitsPayable\": \"3170000\",\n" +
            "        \"LakshyaPremiumPaid\": \"2588157\",\n" +
            "        \"LakshyaBenefitsPayable\": \"20170000\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"Year\": \"39\",\n" +
            "        \"JeevanPremiumPaid\": \"2782300\",\n" +
            "        \"JeevanBenefitsPayable\": \"3070000\",\n" +
            "        \"LakshyaPremiumPaid\": \"2875730\",\n" +
            "        \"LakshyaBenefitsPayable\": \"20070000\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"Year\": \"40\",\n" +
            "        \"JeevanPremiumPaid\": \"3059311\",\n" +
            "        \"JeevanBenefitsPayable\": \"2970000\",\n" +
            "        \"LakshyaPremiumPaid\": \"3163303\",\n" +
            "        \"LakshyaBenefitsPayable\": \"19970000\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"Year\": \"41\",\n" +
            "        \"JeevanPremiumPaid\": \"3336322\",\n" +
            "        \"JeevanBenefitsPayable\": \"2870000\",\n" +
            "        \"LakshyaPremiumPaid\": \"3450876\",\n" +
            "        \"LakshyaBenefitsPayable\": \"19870000\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"Year\": \"42\",\n" +
            "        \"JeevanPremiumPaid\": \"3613333\",\n" +
            "        \"JeevanBenefitsPayable\": \"2770000\",\n" +
            "        \"LakshyaPremiumPaid\": \"3738449\",\n" +
            "        \"LakshyaBenefitsPayable\": \"19770000\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"Year\": \"43\",\n" +
            "        \"JeevanPremiumPaid\": \"3890344\",\n" +
            "        \"JeevanBenefitsPayable\": \"2670000\",\n" +
            "        \"LakshyaPremiumPaid\": \"4026022\",\n" +
            "        \"LakshyaBenefitsPayable\": \"19670000\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"Year\": \"44\",\n" +
            "        \"JeevanPremiumPaid\": \"4167355\",\n" +
            "        \"JeevanBenefitsPayable\": \"2570000\",\n" +
            "        \"LakshyaPremiumPaid\": \"4313595\",\n" +
            "        \"LakshyaBenefitsPayable\": \"19570000\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"Year\": \"45\",\n" +
            "        \"JeevanPremiumPaid\": \"4444366\",\n" +
            "        \"JeevanBenefitsPayable\": \"2470000\",\n" +
            "        \"LakshyaPremiumPaid\": \"4601168\",\n" +
            "        \"LakshyaBenefitsPayable\": \"19470000\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"Year\": \"46\",\n" +
            "        \"JeevanPremiumPaid\": \"4721377\",\n" +
            "        \"JeevanBenefitsPayable\": \"2370000\",\n" +
            "        \"LakshyaPremiumPaid\": \"4888741\",\n" +
            "        \"LakshyaBenefitsPayable\": \"19370000\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"Year\": \"47\",\n" +
            "        \"JeevanPremiumPaid\": \"4721377\",\n" +
            "        \"JeevanBenefitsPayable\": \"2270000\",\n" +
            "        \"LakshyaPremiumPaid\": \"5176314\",\n" +
            "        \"LakshyaBenefitsPayable\": \"19270000\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"Year\": \"48\",\n" +
            "        \"JeevanPremiumPaid\": \"4721377\",\n" +
            "        \"JeevanBenefitsPayable\": \"2170000\",\n" +
            "        \"LakshyaPremiumPaid\": \"5463887\",\n" +
            "        \"LakshyaBenefitsPayable\": \"19170000\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"Year\": \"49\",\n" +
            "        \"JeevanPremiumPaid\": \"4721377\",\n" +
            "        \"JeevanBenefitsPayable\": \"2070000\",\n" +
            "        \"LakshyaPremiumPaid\": \"5751460\",\n" +
            "        \"LakshyaBenefitsPayable\": \"19070000\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"Year\": \"50\",\n" +
            "        \"JeevanPremiumPaid\": \"4721377\",\n" +
            "        \"JeevanBenefitsPayable\": \"0\",\n" +
            "        \"LakshyaPremiumPaid\": \"6039033\",\n" +
            "        \"LakshyaBenefitsPayable\": \"17000000\"\n" +
            "      }\n" +
            "    ],\n" +
            "    \"Benefits\": [\n" +
            "      {\n" +
            "        \"Year\": \"31-03-2019\",\n" +
            "        \"Age\": \"30\",\n" +
            "        \"LicCover\": \"3970000\",\n" +
            "        \"AnnualPremium\": \"283106\",\n" +
            "        \"CashFlow\": \"0\",\n" +
            "        \"LoanAvailable\": \"0\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"Year\": \"31-03-2020\",\n" +
            "        \"Age\": \"31\",\n" +
            "        \"LicCover\": \"3870000\",\n" +
            "        \"AnnualPremium\": \"283106\",\n" +
            "        \"CashFlow\": \"0\",\n" +
            "        \"LoanAvailable\": \"0\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"Year\": \"31-03-2021\",\n" +
            "        \"Age\": \"32\",\n" +
            "        \"LicCover\": \"3770000\",\n" +
            "        \"AnnualPremium\": \"277011\",\n" +
            "        \"CashFlow\": \"0\",\n" +
            "        \"LoanAvailable\": \"229316\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"Year\": \"31-03-2022\",\n" +
            "        \"Age\": \"33\",\n" +
            "        \"LicCover\": \"3670000\",\n" +
            "        \"AnnualPremium\": \"277011\",\n" +
            "        \"CashFlow\": \"0\",\n" +
            "        \"LoanAvailable\": \"529176\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"Year\": \"31-03-2023\",\n" +
            "        \"Age\": \"34\",\n" +
            "        \"LicCover\": \"3570000\",\n" +
            "        \"AnnualPremium\": \"277011\",\n" +
            "        \"CashFlow\": \"0\",\n" +
            "        \"LoanAvailable\": \"664003\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"Year\": \"31-03-2024\",\n" +
            "        \"Age\": \"35\",\n" +
            "        \"LicCover\": \"3470000\",\n" +
            "        \"AnnualPremium\": \"277011\",\n" +
            "        \"CashFlow\": \"0\",\n" +
            "        \"LoanAvailable\": \"799393\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"Year\": \"31-03-2025\",\n" +
            "        \"Age\": \"36\",\n" +
            "        \"LicCover\": \"3370000\",\n" +
            "        \"AnnualPremium\": \"277011\",\n" +
            "        \"CashFlow\": \"0\",\n" +
            "        \"LoanAvailable\": \"933910\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"Year\": \"31-03-2026\",\n" +
            "        \"Age\": \"37\",\n" +
            "        \"LicCover\": \"3270000\",\n" +
            "        \"AnnualPremium\": \"277011\",\n" +
            "        \"CashFlow\": \"0\",\n" +
            "        \"LoanAvailable\": \"1119611\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"Year\": \"31-03-2027\",\n" +
            "        \"Age\": \"38\",\n" +
            "        \"LicCover\": \"3170000\",\n" +
            "        \"AnnualPremium\": \"277011\",\n" +
            "        \"CashFlow\": \"0\",\n" +
            "        \"LoanAvailable\": \"1318469\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"Year\": \"31-03-2028\",\n" +
            "        \"Age\": \"39\",\n" +
            "        \"LicCover\": \"3070000\",\n" +
            "        \"AnnualPremium\": \"277011\",\n" +
            "        \"CashFlow\": \"0\",\n" +
            "        \"LoanAvailable\": \"1530654\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"Year\": \"31-03-2029\",\n" +
            "        \"Age\": \"40\",\n" +
            "        \"LicCover\": \"2970000\",\n" +
            "        \"AnnualPremium\": \"277011\",\n" +
            "        \"CashFlow\": \"0\",\n" +
            "        \"LoanAvailable\": \"1756353\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"Year\": \"31-03-2030\",\n" +
            "        \"Age\": \"41\",\n" +
            "        \"LicCover\": \"2870000\",\n" +
            "        \"AnnualPremium\": \"277011\",\n" +
            "        \"CashFlow\": \"0\",\n" +
            "        \"LoanAvailable\": \"1995765\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"Year\": \"31-03-2031\",\n" +
            "        \"Age\": \"42\",\n" +
            "        \"LicCover\": \"2770000\",\n" +
            "        \"AnnualPremium\": \"277011\",\n" +
            "        \"CashFlow\": \"0\",\n" +
            "        \"LoanAvailable\": \"2249209\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"Year\": \"31-03-2032\",\n" +
            "        \"Age\": \"43\",\n" +
            "        \"LicCover\": \"2670000\",\n" +
            "        \"AnnualPremium\": \"277011\",\n" +
            "        \"CashFlow\": \"0\",\n" +
            "        \"LoanAvailable\": \"2516889\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"Year\": \"31-03-2033\",\n" +
            "        \"Age\": \"44\",\n" +
            "        \"LicCover\": \"2570000\",\n" +
            "        \"AnnualPremium\": \"277011\",\n" +
            "        \"CashFlow\": \"0\",\n" +
            "        \"LoanAvailable\": \"2799293\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"Year\": \"31-03-2034\",\n" +
            "        \"Age\": \"45\",\n" +
            "        \"LicCover\": \"2470000\",\n" +
            "        \"AnnualPremium\": \"277011\",\n" +
            "        \"CashFlow\": \"0\",\n" +
            "        \"LoanAvailable\": \"3096872\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"Year\": \"31-03-2035\",\n" +
            "        \"Age\": \"46\",\n" +
            "        \"LicCover\": \"2370000\",\n" +
            "        \"AnnualPremium\": \"277011\",\n" +
            "        \"CashFlow\": \"0\",\n" +
            "        \"LoanAvailable\": \"3410120\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"Year\": \"31-03-2036\",\n" +
            "        \"Age\": \"47\",\n" +
            "        \"LicCover\": \"2270000\",\n" +
            "        \"AnnualPremium\": \"0\",\n" +
            "        \"CashFlow\": \"0\",\n" +
            "        \"LoanAvailable\": \"3542325\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"Year\": \"31-03-2037\",\n" +
            "        \"Age\": \"48\",\n" +
            "        \"LicCover\": \"2170000\",\n" +
            "        \"AnnualPremium\": \"0\",\n" +
            "        \"CashFlow\": \"0\",\n" +
            "        \"LoanAvailable\": \"3682906\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"Year\": \"31-03-2038\",\n" +
            "        \"Age\": \"49\",\n" +
            "        \"LicCover\": \"2070000\",\n" +
            "        \"AnnualPremium\": \"0\",\n" +
            "        \"CashFlow\": \"0\",\n" +
            "        \"LoanAvailable\": \"3733362\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"Year\": \"07-01-2039\",\n" +
            "        \"Age\": \"50\",\n" +
            "        \"LicCover\": \"0\",\n" +
            "        \"AnnualPremium\": \"0\",\n" +
            "        \"CashFlow\": \"0\",\n" +
            "        \"LoanAvailable\": \"0\"\n" +
            "      }\n" +
            "    ],\n" +
            "    \"PageoneUnmatched\": [\n" +
            "      {\n" +
            "        \"AnnualPremiumFirstYr\": \"Rs. 287573\",\n" +
            "        \"AnnualPremiumOtherYrs\": \"Rs. 286173\",\n" +
            "        \"OtherYrs\": \"2 to 17 Years\",\n" +
            "        \"MaturityYear\": \"Maturity After 20 Years\",\n" +
            "        \"LumpsumpDeath\": \"Rs. 50,00,000\",\n" +
            "        \"AnnualTillEOT\": \"Rs. 1,00,000\",\n" +
            "        \"Monthlyterm\": \"Monthly for 20 Years\",\n" +
            "        \"MonthlytermValue\": \"Rs. 50000\",\n" +
            "        \"MaturityDateValue\": \"Rs. 1970000\"\n" +
            "      }\n" +
            "    ],\n" +
            "    \"PageTwoStandAlone\": [\n" +
            "      {\n" +
            "        \"AnnualPremiumFirstYr\": \"Rs. 283106\",\n" +
            "        \"AnnualPremiumOtherYrs\": \"Rs. 270915\",\n" +
            "        \"OtherYrs\": \"2 to 17 Years\",\n" +
            "        \"MaturityYear\": \"Maturity After 20 Years\",\n" +
            "        \"LumpsumpDeath\": \"NIL\",\n" +
            "        \"AnnualTillEOT\": \"Rs. 1,00,000\",\n" +
            "        \"Monthlyterm\": \"Monthly for 20 Years\",\n" +
            "        \"MonthlytermValue\": \"NIL\",\n" +
            "        \"MaturityDateValue\": \"Rs. 1970000\"\n" +
            "      }\n" +
            "    ],\n" +
            "    \"ProductCombo\": [\n" +
            "      {\n" +
            "        \"LicTerm\": \"20\",\n" +
            "        \"LicPPT\": \"17\",\n" +
            "        \"LicMode\": \"Y\",\n" +
            "        \"LicSum\": \"1000000\",\n" +
            "        \"LicPremYearOne\": \"283106\",\n" +
            "        \"LicPremOtherYears\": \"270915\",\n" +
            "        \"LicYears\": \"2 to 17 Years\",\n" +
            "        \"HdfcTerm\": \"20\",\n" +
            "        \"HdfcPPT\": \"17\",\n" +
            "        \"HdfcMode\": \"Y\",\n" +
            "        \"HdfcSum\": \"5000000\",\n" +
            "        \"HdfcPremYearOne\": \"4467\",\n" +
            "        \"HdfcPremOtherYears\": \"4467\",\n" +
            "        \"HdfcYears\": \"2 to 17 Years\",\n" +
            "        \"TotalOne\": \"Rs. 287573\",\n" +
            "        \"TotalTwo\": \"Rs. 286173\"\n" +
            "      }\n" +
            "    ],\n" +
            "    \"LicVrs\": [\n" +
            "      {\n" +
            "        \"BasicSum\": \"1000000\",\n" +
            "        \"LicAnnualPremium\": \"283106\",\n" +
            "        \"UlAnnualPremium\": \"287573\",\n" +
            "        \"LicMaturityAmt\": \"Rs. 1100000 + Bonuses + final Bonus (If any) on maturity date\",\n" +
            "        \"UlMaturityAmt\": \"Rs. 1100000 + Bonuses + final Bonus (If any) on maturity date\",\n" +
            "        \"LicAnnualIcomeOndeath\": \"Guranteed Rs. 1,00,000 every year till maturity.\",\n" +
            "        \"UlAnnualIcomeOndeath\": \"Guranteed Rs. 1,00,000 every year till maturity.\"\n" +
            "      }\n" +
            "    ],\n" +
            "    \"BenefitsPopup\": [\n" +
            "      {\n" +
            "        \"Term\": \"20\",\n" +
            "        \"ULOnDeath\": \"5000000\",\n" +
            "        \"AnnualPayout\": \"100000\"\n" +
            "      }\n" +
            "    ]\n" +
            "  }\n" +
            "}";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gson_json);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnJson = (Button) findViewById(R.id.btnJson);
        btnGson = (Button) findViewById(R.id.btnGson);


        btnJson.setOnClickListener(this);
        btnGson.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.btnJson)
        {

            setGsonFromJson();
        }else{

        }
    }


    public boolean setGsonFromJson() {
        try {

            GsonResponse convertedObject = new Gson().fromJson(test, GsonResponse.class);
            MasterDataBean masterData = convertedObject.getMasterData();

            Toast.makeText(this,masterData.getBenefits().get(0).getAge(),Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


}
