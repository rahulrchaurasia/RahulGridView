package com.example.rahul.gridview.core.response;


import com.example.rahul.gridview.core.APIResponse;
import com.example.rahul.gridview.model.EmployeeEntity;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by IN-RB on 20-04-2017.
 */

public class ProductResponse extends APIResponse {


    /**
     * message :
     * result : {"empCode":"RB40000428","lstLeads":[{"ProdType":"","custName":"hahah","leadId":365481,"loanAmnt":545454,"mobNo":"9121338219","status":"Already Taken"},{"ProdType":"","custName":"gshss","leadId":365480,"loanAmnt":4554,"mobNo":"+918698774911","status":"Already Taken"},{"ProdType":"","custName":"yaga","leadId":365479,"loanAmnt":0,"mobNo":"9133311330","status":"Already Taken"},{"ProdType":"","custName":"ycaycau ","leadId":365478,"loanAmnt":5757,"mobNo":"9113331100","status":"Already Taken"},{"ProdType":"","custName":"gzgg bzh","leadId":365442,"loanAmnt":54,"mobNo":"7123665990","status":"Already Taken"},{"ProdType":"","custName":"hh yg","leadId":365441,"loanAmnt":55555,"mobNo":"7123142222","status":"Already Taken"},{"ProdType":"","custName":"tanmay patel16","leadId":365439,"loanAmnt":0,"mobNo":"7123142991","status":"Already Taken"},{"ProdType":"","custName":"tanmay patel15","leadId":365438,"loanAmnt":0,"mobNo":"7123142990","status":"New"},{"ProdType":"","custName":"tanmay patel14","leadId":365437,"loanAmnt":0,"mobNo":"7123142989","status":"New"},{"ProdType":"","custName":"tanmay patel13","leadId":365436,"loanAmnt":0,"mobNo":"7123142988","status":"New"},{"ProdType":"","custName":"tanmay patel12","leadId":365435,"loanAmnt":0,"mobNo":"7123142987","status":"New"},{"ProdType":"","custName":"tanmay patel11","leadId":365434,"loanAmnt":0,"mobNo":"7123142986","status":"New"},{"ProdType":"","custName":"tanmay patel10","leadId":365433,"loanAmnt":0,"mobNo":"7123142985","status":"New"},{"ProdType":"","custName":"tanmay patel9","leadId":365432,"loanAmnt":0,"mobNo":"7123142984","status":"Already Taken"},{"ProdType":"","custName":"tanmay patel8","leadId":365431,"loanAmnt":0,"mobNo":"7123142983","status":"New"},{"ProdType":"","custName":"tanmay patel7","leadId":365430,"loanAmnt":0,"mobNo":"7123142982","status":"New"},{"ProdType":"","custName":"tanmay patel6","leadId":365429,"loanAmnt":0,"mobNo":"7123142981","status":"New"},{"ProdType":"","custName":"tanmay patel5","leadId":365428,"loanAmnt":0,"mobNo":"7123142980","status":"New"},{"ProdType":"","custName":"tanmay patel4","leadId":365427,"loanAmnt":0,"mobNo":"7123142979","status":"Enquiry Not Made"},{"ProdType":"","custName":"tanmay patel3","leadId":365426,"loanAmnt":0,"mobNo":"7123142978","status":"Just Enquiry"},{"ProdType":"","custName":"tanmay patel2","leadId":365425,"loanAmnt":0,"mobNo":"7123142977","status":"Already Taken"},{"ProdType":"","custName":"tanmay patel1","leadId":365424,"loanAmnt":0,"mobNo":"7123142976","status":"Already Taken"},{"ProdType":"","custName":"sfgs vsgs","leadId":365400,"loanAmnt":545454,"mobNo":"9123394125","status":"Already Taken"},{"ProdType":"","custName":"tara","leadId":365397,"loanAmnt":5444,"mobNo":"9111234188","status":"Already Taken"},{"ProdType":"","custName":"gav hssg","leadId":365394,"loanAmnt":5757,"mobNo":"9123445122","status":"Already Taken"},{"ProdType":"","custName":"fucf","leadId":365388,"loanAmnt":8668,"mobNo":"5555","status":"Already Taken"},{"ProdType":"","custName":"fyff","leadId":365364,"loanAmnt":0,"mobNo":"1255854","status":"Already Taken"},{"ProdType":"","custName":"gyhgg","leadId":365358,"loanAmnt":0,"mobNo":"125585","status":"Already Taken"},{"ProdType":"","custName":"gyfuv","leadId":365353,"loanAmnt":0,"mobNo":"1255856","status":"Already Taken"},{"ProdType":"","custName":"fl visit","leadId":365116,"loanAmnt":878787,"mobNo":"7111333331","status":"Already Taken"},{"ProdType":"","custName":"hzhzh","leadId":365032,"loanAmnt":878757,"mobNo":"9123445612","status":"Already Taken"},{"ProdType":"","custName":"tanmay patel16","leadId":365026,"loanAmnt":0,"mobNo":"7121142991","status":"Already Taken"},{"ProdType":"","custName":"tanmay patel15","leadId":365025,"loanAmnt":0,"mobNo":"7121142990","status":"Already Taken"},{"ProdType":"","custName":"tanmay patel14","leadId":365024,"loanAmnt":0,"mobNo":"7121142989","status":"Already Taken"},{"ProdType":"","custName":"tanmay patel13","leadId":365023,"loanAmnt":0,"mobNo":"7121142988","status":"Already Taken"},{"ProdType":"","custName":"tanmay patel12","leadId":365022,"loanAmnt":0,"mobNo":"7121142987","status":"Already Taken"},{"ProdType":"","custName":"tanmay patel11","leadId":365021,"loanAmnt":0,"mobNo":"7121142986","status":"Already Taken"},{"ProdType":"","custName":"tanmay patel10","leadId":365020,"loanAmnt":0,"mobNo":"7121142985","status":"Already Taken"},{"ProdType":"","custName":"tanmay patel9","leadId":365019,"loanAmnt":0,"mobNo":"7121142984","status":"Already Taken"},{"ProdType":"","custName":"tanmay patel8","leadId":365018,"loanAmnt":0,"mobNo":"7121142983","status":"Already Taken"},{"ProdType":"","custName":"tanmay patel7","leadId":365017,"loanAmnt":0,"mobNo":"7121142982","status":"Already Taken"},{"ProdType":"","custName":"tanmay patel6","leadId":365016,"loanAmnt":0,"mobNo":"7121142981","status":"Already Taken"},{"ProdType":"","custName":"tanmay patel5","leadId":365015,"loanAmnt":0,"mobNo":"7121142980","status":"Already Taken"},{"ProdType":"","custName":"tanmay patel4","leadId":365014,"loanAmnt":0,"mobNo":"7121142979","status":"Already Taken"},{"ProdType":"","custName":"tanmay patel3","leadId":365013,"loanAmnt":0,"mobNo":"7121142978","status":"Already Taken"},{"ProdType":"","custName":"tanmay patel2","leadId":365012,"loanAmnt":0,"mobNo":"7121142977","status":"Already Taken"},{"ProdType":"","custName":"tanmay patel1","leadId":365011,"loanAmnt":0,"mobNo":"7121142976","status":"Already Taken"},{"ProdType":"","custName":"cust care","leadId":360086,"loanAmnt":600000,"mobNo":"198","status":"Already Taken"},{"ProdType":"","custName":"amit yadav","leadId":360077,"loanAmnt":89898989,"mobNo":"9898998911","status":"Already Taken"}],"totalCount":49}
     * statusId : 0
     */


    private EmployeeEntity result;

    public EmployeeEntity getResult() {
        return result;
    }

    public void setResult(EmployeeEntity result) {
        this.result = result;
    }




}
