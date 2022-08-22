package com.example.rahul.gridview.Activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.view.ActionMode;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rahul.gridview.Adapter.LeadAdapter;
import com.example.rahul.gridview.R;
import com.example.rahul.gridview.Utility.DividerItemDecoration;
import com.example.rahul.gridview.Utility.Utility;
import com.example.rahul.gridview.core.APIResponse;
import com.example.rahul.gridview.core.IResponseSubcriber;
import com.example.rahul.gridview.core.controller.sync.SyncController;
import com.example.rahul.gridview.core.response.ProductResponse;
import com.example.rahul.gridview.model.EmployeeEntity;
import com.example.rahul.gridview.model.LeadEntity;
import com.example.rahul.gridview.model.Person;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmWithJson extends BaseActivity implements IResponseSubcriber {

    TextView txtCode, txtCount;
    LinearLayout lvParent;
    RecyclerView recyclerLead;
    TextView mSearchText;
    List<LeadEntity> lsLeadEntity;
    List<LeadEntity> lstResult ;
    LeadAdapter mAdapter;
    private Realm realm;
    private SearchView.OnQueryTextListener queryTextListener;     // for Option Menu

    private ActionModeCallback actionModeCallback;       // apply
    private ActionMode actionMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realm_with_json);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        realm = Realm.getDefaultInstance();

        initialize();

      //  showProgressDialog();
      //  new SyncController(this, realm).getProduct("0", "RB40000628", "1", this);
        //  lsLeadEntity= getLeadList();

        lsLeadEntity =getLeadList();

        if (lsLeadEntity.size() >0) {
            LeadEntity leadEntity = lsLeadEntity.get(0);
            txtCode.setText(""+leadEntity.getCustName());
            txtCount.setText("" + lsLeadEntity.size());
//
//            lsLeadEntity = employeeEntity.getLstLeads();

            mAdapter = new LeadAdapter(this, lsLeadEntity, realm);
            recyclerLead.setAdapter(mAdapter);
            mAdapter.resetAll();     // for reset the checkBox which has set by Realm
        }
        else {
            Snackbar.make(recyclerLead,"No data available",Snackbar.LENGTH_INDEFINITE).show();
        }


    }

    private void initialize() {

        txtCode = (TextView) findViewById(R.id.txtCode);
        txtCount = (TextView) findViewById(R.id.txtCount);
        lvParent = (LinearLayout)findViewById(R.id.lvParent);

        recyclerLead = (RecyclerView) findViewById(R.id.rvLead);
        recyclerLead.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(RealmWithJson.this);
        recyclerLead.setLayoutManager(layoutManager);
        recyclerLead.setItemAnimator(new DefaultItemAnimator());

      //  recyclerLead.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));

        actionModeCallback = new ActionModeCallback();
        lstResult = new ArrayList<LeadEntity>();

    }



    private List<EmployeeEntity> getEmployee() {
        //  List<LeadEntity> lstLeadListAll = getRealm().where(LeadEntity.class).findAll();
        RealmResults<EmployeeEntity> lstEmployeeAll = realm.where(EmployeeEntity.class).findAll();
        return lstEmployeeAll;
    }

    private List<LeadEntity> getLeadList() {
        List<LeadEntity> lstLeadListAll = realm.where(LeadEntity.class).findAll();

        return lstLeadListAll;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.search_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchManager searchManager = (SearchManager) this.getSystemService(Context.SEARCH_SERVICE);

        SearchView searchView = null;
        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(this.getComponentName()));
        }

        if (searchView != null  && lsLeadEntity !=null && lsLeadEntity.size() > 0) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(this.getComponentName()));

            queryTextListener = new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextChange(String newText) {

                    if (newText.length() > 0) {
                        mAdapter.filer(newText);
                    } else {
                        mAdapter.findAll(getLeadList());
                    }
                    return true;
                }

                @Override
                public boolean onQueryTextSubmit(String query) {
                    return true;
                }
            };
            searchView.setOnQueryTextListener(queryTextListener);
        }
        return super.onCreateOptionsMenu(menu);

    }

    public void onIconClicked(LeadEntity leadEntity ,boolean selected)
    {
       // Toast.makeText(this,"Pos" + leadEntity.getCustName(), Toast.LENGTH_SHORT).show();
        if(actionMode == null)
        {
            actionMode = startSupportActionMode(actionModeCallback);
        }

        toggleSelection(leadEntity,selected);
    }

    private void toggleSelection(LeadEntity leadEntity, boolean selected)
    {
          if(selected)
          {
              lstResult.add(leadEntity);
          }else {
              int indexOfSelected = 0;
              for (int i = 0; i < lstResult.size(); i++) {

                  if (lstResult.get(i).getLeadId() == (leadEntity.getLeadId())) {
                      indexOfSelected = i;
                      break;
                  }
              }
              lstResult.remove(indexOfSelected);

          }

          int count = lstResult.size();
          if(count == 0)
          {
              actionMode.finish();
          }else {
              actionMode.setTitle(String.valueOf(count));
              actionMode.invalidate();
          }

    }

    public int getSelectedSize()
    {
       return lstResult.size();
    }

    @Override
    public void OnSuccess(APIResponse response, String message) throws InterruptedException {

        cancelDialog();
        if (response instanceof ProductResponse)
            if (response.getStatusId() == 0) {
                if (((ProductResponse) response).getResult() != null) {


                    EmployeeEntity employeeEntity =((ProductResponse) response).getResult();

                    if (employeeEntity != null) {
                      //  EmployeeEntity employeeEntity = lstEntity.get(0);
                        txtCode.setText(employeeEntity.getEmpCode());
                        txtCount.setText("" + employeeEntity.getTotalCount());

                        lsLeadEntity = employeeEntity.getLstLeads();

                        mAdapter = new LeadAdapter(this, lsLeadEntity, realm);
                        recyclerLead.setAdapter(mAdapter);
                        mAdapter.resetAll();     // for reset the checkBox which has set by Realm
                    }
                    else {
                        Snackbar.make(recyclerLead,"No data available",Snackbar.LENGTH_INDEFINITE).show();
                    }

                }
            } else {

                Snackbar.make(recyclerLead, "No data available", Snackbar.LENGTH_SHORT).show();
            }
    }

    @Override
    public void OnFailure(Throwable t) {
        cancelDialog();

        Snackbar.make(recyclerLead, "No data available", Snackbar.LENGTH_SHORT).show();
    }

    //Contextual menu item
    //region Action Mode CallBack Interface
    private class ActionModeCallback implements ActionMode.Callback {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.menu_action_mode, menu);

            // disable Parent layout if action mode is enabled
            lvParent.setEnabled(false);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_delete:
                    // delete all the selected messages
                    deleteMessages();
                    mode.finish();
                    return true;

                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
          //  mAdapter.clearSelections();
            lstResult.clear();
            lvParent.setEnabled(true);
            actionMode = null;
            recyclerLead.post(new Runnable() {
                @Override
                public void run() {
                    mAdapter.resetAll();
                    mAdapter.notifyDataSetChanged();
                }
            });
        }
    }
    //endregion

    private void deleteMessages()
    {
          mAdapter.deleteRecord();

    }

    // In our case data is pull from realm db .
    // Hende color is also update on success of Controller ie( save in db )

    // region Not in Used
    private  void setColorLead(){

        for( LeadEntity leadEntity : lsLeadEntity)
        {
            leadEntity.setColor(getRandomMaterialColor("400"));
        }

    }

    private int getRandomMaterialColor(String typeColor) {
        int returnColor = Color.GRAY;
        int arrayId = getResources().getIdentifier("mdcolor_" + typeColor, "array", getPackageName());

        if (arrayId != 0) {
            TypedArray colors = getResources().obtainTypedArray(arrayId);
            int index = (int) (Math.random() * colors.length());
            returnColor = colors.getColor(index, Color.GRAY);
            colors.recycle();
        }
        return returnColor;
    }

    //endregion

}
