package com.example.rahul.gridview.core.response;

import java.util.List;

public  class MasterDataBean {
        private List<DeathBenefitBean> DeathBenefit;
        private List<BenefitsBean> Benefits;
        private List<PageoneUnmatchedBean> PageoneUnmatched;
        private List<PageTwoStandAloneBean> PageTwoStandAlone;
        private List<ProductComboBean> ProductCombo;
        private List<LicVrsBean> LicVrs;
        private List<BenefitsPopupBean> BenefitsPopup;

        public List<DeathBenefitBean> getDeathBenefit() {
            return DeathBenefit;
        }

        public void setDeathBenefit(List<DeathBenefitBean> DeathBenefit) {
            this.DeathBenefit = DeathBenefit;
        }

        public List<BenefitsBean> getBenefits() {
            return Benefits;
        }

        public void setBenefits(List<BenefitsBean> Benefits) {
            this.Benefits = Benefits;
        }

        public List<PageoneUnmatchedBean> getPageoneUnmatched() {
            return PageoneUnmatched;
        }

        public void setPageoneUnmatched(List<PageoneUnmatchedBean> PageoneUnmatched) {
            this.PageoneUnmatched = PageoneUnmatched;
        }

        public List<PageTwoStandAloneBean> getPageTwoStandAlone() {
            return PageTwoStandAlone;
        }

        public void setPageTwoStandAlone(List<PageTwoStandAloneBean> PageTwoStandAlone) {
            this.PageTwoStandAlone = PageTwoStandAlone;
        }

        public List<ProductComboBean> getProductCombo() {
            return ProductCombo;
        }

        public void setProductCombo(List<ProductComboBean> ProductCombo) {
            this.ProductCombo = ProductCombo;
        }

        public List<LicVrsBean> getLicVrs() {
            return LicVrs;
        }

        public void setLicVrs(List<LicVrsBean> LicVrs) {
            this.LicVrs = LicVrs;
        }

        public List<BenefitsPopupBean> getBenefitsPopup() {
            return BenefitsPopup;
        }

        public void setBenefitsPopup(List<BenefitsPopupBean> BenefitsPopup) {
            this.BenefitsPopup = BenefitsPopup;
        }

        public static class DeathBenefitBean {
            /**
             * Year : 30
             * JeevanPremiumPaid : 283106
             * JeevanBenefitsPayable : 3970000
             * LakshyaPremiumPaid : 0287573
             * LakshyaBenefitsPayable : 20970000
             */

            private String Year;
            private String JeevanPremiumPaid;
            private String JeevanBenefitsPayable;
            private String LakshyaPremiumPaid;
            private String LakshyaBenefitsPayable;

            public String getYear() {
                return Year;
            }

            public void setYear(String Year) {
                this.Year = Year;
            }

            public String getJeevanPremiumPaid() {
                return JeevanPremiumPaid;
            }

            public void setJeevanPremiumPaid(String JeevanPremiumPaid) {
                this.JeevanPremiumPaid = JeevanPremiumPaid;
            }

            public String getJeevanBenefitsPayable() {
                return JeevanBenefitsPayable;
            }

            public void setJeevanBenefitsPayable(String JeevanBenefitsPayable) {
                this.JeevanBenefitsPayable = JeevanBenefitsPayable;
            }

            public String getLakshyaPremiumPaid() {
                return LakshyaPremiumPaid;
            }

            public void setLakshyaPremiumPaid(String LakshyaPremiumPaid) {
                this.LakshyaPremiumPaid = LakshyaPremiumPaid;
            }

            public String getLakshyaBenefitsPayable() {
                return LakshyaBenefitsPayable;
            }

            public void setLakshyaBenefitsPayable(String LakshyaBenefitsPayable) {
                this.LakshyaBenefitsPayable = LakshyaBenefitsPayable;
            }
        }

        public static class BenefitsBean {
            /**
             * Year : 31-03-2019
             * Age : 30
             * LicCover : 3970000
             * AnnualPremium : 283106
             * CashFlow : 0
             * LoanAvailable : 0
             */

            private String Year;
            private String Age;
            private String LicCover;
            private String AnnualPremium;
            private String CashFlow;
            private String LoanAvailable;

            public String getYear() {
                return Year;
            }

            public void setYear(String Year) {
                this.Year = Year;
            }

            public String getAge() {
                return Age;
            }

            public void setAge(String Age) {
                this.Age = Age;
            }

            public String getLicCover() {
                return LicCover;
            }

            public void setLicCover(String LicCover) {
                this.LicCover = LicCover;
            }

            public String getAnnualPremium() {
                return AnnualPremium;
            }

            public void setAnnualPremium(String AnnualPremium) {
                this.AnnualPremium = AnnualPremium;
            }

            public String getCashFlow() {
                return CashFlow;
            }

            public void setCashFlow(String CashFlow) {
                this.CashFlow = CashFlow;
            }

            public String getLoanAvailable() {
                return LoanAvailable;
            }

            public void setLoanAvailable(String LoanAvailable) {
                this.LoanAvailable = LoanAvailable;
            }
        }

        public static class PageoneUnmatchedBean {
            /**
             * AnnualPremiumFirstYr : Rs. 287573
             * AnnualPremiumOtherYrs : Rs. 286173
             * OtherYrs : 2 to 17 Years
             * MaturityYear : Maturity After 20 Years
             * LumpsumpDeath : Rs. 50,00,000
             * AnnualTillEOT : Rs. 1,00,000
             * Monthlyterm : Monthly for 20 Years
             * MonthlytermValue : Rs. 50000
             * MaturityDateValue : Rs. 1970000
             */

            private String AnnualPremiumFirstYr;
            private String AnnualPremiumOtherYrs;
            private String OtherYrs;
            private String MaturityYear;
            private String LumpsumpDeath;
            private String AnnualTillEOT;
            private String Monthlyterm;
            private String MonthlytermValue;
            private String MaturityDateValue;

            public String getAnnualPremiumFirstYr() {
                return AnnualPremiumFirstYr;
            }

            public void setAnnualPremiumFirstYr(String AnnualPremiumFirstYr) {
                this.AnnualPremiumFirstYr = AnnualPremiumFirstYr;
            }

            public String getAnnualPremiumOtherYrs() {
                return AnnualPremiumOtherYrs;
            }

            public void setAnnualPremiumOtherYrs(String AnnualPremiumOtherYrs) {
                this.AnnualPremiumOtherYrs = AnnualPremiumOtherYrs;
            }

            public String getOtherYrs() {
                return OtherYrs;
            }

            public void setOtherYrs(String OtherYrs) {
                this.OtherYrs = OtherYrs;
            }

            public String getMaturityYear() {
                return MaturityYear;
            }

            public void setMaturityYear(String MaturityYear) {
                this.MaturityYear = MaturityYear;
            }

            public String getLumpsumpDeath() {
                return LumpsumpDeath;
            }

            public void setLumpsumpDeath(String LumpsumpDeath) {
                this.LumpsumpDeath = LumpsumpDeath;
            }

            public String getAnnualTillEOT() {
                return AnnualTillEOT;
            }

            public void setAnnualTillEOT(String AnnualTillEOT) {
                this.AnnualTillEOT = AnnualTillEOT;
            }

            public String getMonthlyterm() {
                return Monthlyterm;
            }

            public void setMonthlyterm(String Monthlyterm) {
                this.Monthlyterm = Monthlyterm;
            }

            public String getMonthlytermValue() {
                return MonthlytermValue;
            }

            public void setMonthlytermValue(String MonthlytermValue) {
                this.MonthlytermValue = MonthlytermValue;
            }

            public String getMaturityDateValue() {
                return MaturityDateValue;
            }

            public void setMaturityDateValue(String MaturityDateValue) {
                this.MaturityDateValue = MaturityDateValue;
            }
        }

        public static class PageTwoStandAloneBean {
            /**
             * AnnualPremiumFirstYr : Rs. 283106
             * AnnualPremiumOtherYrs : Rs. 270915
             * OtherYrs : 2 to 17 Years
             * MaturityYear : Maturity After 20 Years
             * LumpsumpDeath : NIL
             * AnnualTillEOT : Rs. 1,00,000
             * Monthlyterm : Monthly for 20 Years
             * MonthlytermValue : NIL
             * MaturityDateValue : Rs. 1970000
             */

            private String AnnualPremiumFirstYr;
            private String AnnualPremiumOtherYrs;
            private String OtherYrs;
            private String MaturityYear;
            private String LumpsumpDeath;
            private String AnnualTillEOT;
            private String Monthlyterm;
            private String MonthlytermValue;
            private String MaturityDateValue;

            public String getAnnualPremiumFirstYr() {
                return AnnualPremiumFirstYr;
            }

            public void setAnnualPremiumFirstYr(String AnnualPremiumFirstYr) {
                this.AnnualPremiumFirstYr = AnnualPremiumFirstYr;
            }

            public String getAnnualPremiumOtherYrs() {
                return AnnualPremiumOtherYrs;
            }

            public void setAnnualPremiumOtherYrs(String AnnualPremiumOtherYrs) {
                this.AnnualPremiumOtherYrs = AnnualPremiumOtherYrs;
            }

            public String getOtherYrs() {
                return OtherYrs;
            }

            public void setOtherYrs(String OtherYrs) {
                this.OtherYrs = OtherYrs;
            }

            public String getMaturityYear() {
                return MaturityYear;
            }

            public void setMaturityYear(String MaturityYear) {
                this.MaturityYear = MaturityYear;
            }

            public String getLumpsumpDeath() {
                return LumpsumpDeath;
            }

            public void setLumpsumpDeath(String LumpsumpDeath) {
                this.LumpsumpDeath = LumpsumpDeath;
            }

            public String getAnnualTillEOT() {
                return AnnualTillEOT;
            }

            public void setAnnualTillEOT(String AnnualTillEOT) {
                this.AnnualTillEOT = AnnualTillEOT;
            }

            public String getMonthlyterm() {
                return Monthlyterm;
            }

            public void setMonthlyterm(String Monthlyterm) {
                this.Monthlyterm = Monthlyterm;
            }

            public String getMonthlytermValue() {
                return MonthlytermValue;
            }

            public void setMonthlytermValue(String MonthlytermValue) {
                this.MonthlytermValue = MonthlytermValue;
            }

            public String getMaturityDateValue() {
                return MaturityDateValue;
            }

            public void setMaturityDateValue(String MaturityDateValue) {
                this.MaturityDateValue = MaturityDateValue;
            }
        }

        public static class ProductComboBean {
            /**
             * LicTerm : 20
             * LicPPT : 17
             * LicMode : Y
             * LicSum : 1000000
             * LicPremYearOne : 283106
             * LicPremOtherYears : 270915
             * LicYears : 2 to 17 Years
             * HdfcTerm : 20
             * HdfcPPT : 17
             * HdfcMode : Y
             * HdfcSum : 5000000
             * HdfcPremYearOne : 4467
             * HdfcPremOtherYears : 4467
             * HdfcYears : 2 to 17 Years
             * TotalOne : Rs. 287573
             * TotalTwo : Rs. 286173
             */

            private String LicTerm;
            private String LicPPT;
            private String LicMode;
            private String LicSum;
            private String LicPremYearOne;
            private String LicPremOtherYears;
            private String LicYears;
            private String HdfcTerm;
            private String HdfcPPT;
            private String HdfcMode;
            private String HdfcSum;
            private String HdfcPremYearOne;
            private String HdfcPremOtherYears;
            private String HdfcYears;
            private String TotalOne;
            private String TotalTwo;

            public String getLicTerm() {
                return LicTerm;
            }

            public void setLicTerm(String LicTerm) {
                this.LicTerm = LicTerm;
            }

            public String getLicPPT() {
                return LicPPT;
            }

            public void setLicPPT(String LicPPT) {
                this.LicPPT = LicPPT;
            }

            public String getLicMode() {
                return LicMode;
            }

            public void setLicMode(String LicMode) {
                this.LicMode = LicMode;
            }

            public String getLicSum() {
                return LicSum;
            }

            public void setLicSum(String LicSum) {
                this.LicSum = LicSum;
            }

            public String getLicPremYearOne() {
                return LicPremYearOne;
            }

            public void setLicPremYearOne(String LicPremYearOne) {
                this.LicPremYearOne = LicPremYearOne;
            }

            public String getLicPremOtherYears() {
                return LicPremOtherYears;
            }

            public void setLicPremOtherYears(String LicPremOtherYears) {
                this.LicPremOtherYears = LicPremOtherYears;
            }

            public String getLicYears() {
                return LicYears;
            }

            public void setLicYears(String LicYears) {
                this.LicYears = LicYears;
            }

            public String getHdfcTerm() {
                return HdfcTerm;
            }

            public void setHdfcTerm(String HdfcTerm) {
                this.HdfcTerm = HdfcTerm;
            }

            public String getHdfcPPT() {
                return HdfcPPT;
            }

            public void setHdfcPPT(String HdfcPPT) {
                this.HdfcPPT = HdfcPPT;
            }

            public String getHdfcMode() {
                return HdfcMode;
            }

            public void setHdfcMode(String HdfcMode) {
                this.HdfcMode = HdfcMode;
            }

            public String getHdfcSum() {
                return HdfcSum;
            }

            public void setHdfcSum(String HdfcSum) {
                this.HdfcSum = HdfcSum;
            }

            public String getHdfcPremYearOne() {
                return HdfcPremYearOne;
            }

            public void setHdfcPremYearOne(String HdfcPremYearOne) {
                this.HdfcPremYearOne = HdfcPremYearOne;
            }

            public String getHdfcPremOtherYears() {
                return HdfcPremOtherYears;
            }

            public void setHdfcPremOtherYears(String HdfcPremOtherYears) {
                this.HdfcPremOtherYears = HdfcPremOtherYears;
            }

            public String getHdfcYears() {
                return HdfcYears;
            }

            public void setHdfcYears(String HdfcYears) {
                this.HdfcYears = HdfcYears;
            }

            public String getTotalOne() {
                return TotalOne;
            }

            public void setTotalOne(String TotalOne) {
                this.TotalOne = TotalOne;
            }

            public String getTotalTwo() {
                return TotalTwo;
            }

            public void setTotalTwo(String TotalTwo) {
                this.TotalTwo = TotalTwo;
            }
        }

        public static class LicVrsBean {
            /**
             * BasicSum : 1000000
             * LicAnnualPremium : 283106
             * UlAnnualPremium : 287573
             * LicMaturityAmt : Rs. 1000001000000 + Bonuses + final Bonus (If any) on maturity date
             * UlMaturityAmt : Rs. 1000001000000 + Bonuses + final Bonus (If any) on maturity date
             * LicAnnualIcomeOndeath : Guranteed Rs. 1,00,000 every year till maturity.
             * UlAnnualIcomeOndeath : Guranteed Rs. 1,00,000 every year till maturity.
             */

            private String BasicSum;
            private String LicAnnualPremium;
            private String UlAnnualPremium;
            private String LicMaturityAmt;
            private String UlMaturityAmt;
            private String LicAnnualIcomeOndeath;
            private String UlAnnualIcomeOndeath;

            public String getBasicSum() {
                return BasicSum;
            }

            public void setBasicSum(String BasicSum) {
                this.BasicSum = BasicSum;
            }

            public String getLicAnnualPremium() {
                return LicAnnualPremium;
            }

            public void setLicAnnualPremium(String LicAnnualPremium) {
                this.LicAnnualPremium = LicAnnualPremium;
            }

            public String getUlAnnualPremium() {
                return UlAnnualPremium;
            }

            public void setUlAnnualPremium(String UlAnnualPremium) {
                this.UlAnnualPremium = UlAnnualPremium;
            }

            public String getLicMaturityAmt() {
                return LicMaturityAmt;
            }

            public void setLicMaturityAmt(String LicMaturityAmt) {
                this.LicMaturityAmt = LicMaturityAmt;
            }

            public String getUlMaturityAmt() {
                return UlMaturityAmt;
            }

            public void setUlMaturityAmt(String UlMaturityAmt) {
                this.UlMaturityAmt = UlMaturityAmt;
            }

            public String getLicAnnualIcomeOndeath() {
                return LicAnnualIcomeOndeath;
            }

            public void setLicAnnualIcomeOndeath(String LicAnnualIcomeOndeath) {
                this.LicAnnualIcomeOndeath = LicAnnualIcomeOndeath;
            }

            public String getUlAnnualIcomeOndeath() {
                return UlAnnualIcomeOndeath;
            }

            public void setUlAnnualIcomeOndeath(String UlAnnualIcomeOndeath) {
                this.UlAnnualIcomeOndeath = UlAnnualIcomeOndeath;
            }
        }

        public static class BenefitsPopupBean {
            /**
             * Term : 20
             * ULOnDeath : 5000000
             * AnnualPayout : 100000
             */

            private String Term;
            private String ULOnDeath;
            private String AnnualPayout;

            public String getTerm() {
                return Term;
            }

            public void setTerm(String Term) {
                this.Term = Term;
            }

            public String getULOnDeath() {
                return ULOnDeath;
            }

            public void setULOnDeath(String ULOnDeath) {
                this.ULOnDeath = ULOnDeath;
            }

            public String getAnnualPayout() {
                return AnnualPayout;
            }

            public void setAnnualPayout(String AnnualPayout) {
                this.AnnualPayout = AnnualPayout;
            }
        }
    }