package com.example.rahul.gridview.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rahul.gridview.R;
import com.example.rahul.gridview.Utility.FlipAnimator;
import com.example.rahul.gridview.model.LeadEntity;

import java.util.List;

import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by IN-RB on 21-04-2017.
 */

public class MyLeadAdapter extends RecyclerView.Adapter<MyLeadAdapter.LeadItem> {


    Activity mContext;
    List<LeadEntity> LeadEntityList;
    private LayoutInflater inflater;


    //Note :Changing Realm data can only be done from inside a transaction

    public MyLeadAdapter(Activity mContext, List<LeadEntity> leadEntityList) {
        this.mContext = mContext;
        LeadEntityList = leadEntityList;

    }

    public class LeadItem extends RecyclerView.ViewHolder {

        public TextView txtCustom, txtlead, txtAmnt, txtMobile, txtStatus ,iconText;
        public LinearLayout lvlead1;
        public ImageView iconImp, imgProfile;
        public RelativeLayout lvlead ,iconContainer, iconBack, iconFront;
        public LeadItem(View itemView) {
            super(itemView);

            txtCustom = (TextView) itemView.findViewById(R.id.txtCustom);
            txtlead = (TextView) itemView.findViewById(R.id.txtlead);
            txtAmnt = (TextView) itemView.findViewById(R.id.txtAmnt);
            txtMobile = (TextView) itemView.findViewById(R.id.txtMobile);
            txtStatus = (TextView) itemView.findViewById(R.id.txtStatus);
            iconText = (TextView) itemView.findViewById(R.id.icon_text);
            lvlead = (RelativeLayout) itemView.findViewById(R.id.lvlead);
            imgProfile = (ImageView) itemView.findViewById(R.id.icon_profile);

            iconContainer = (RelativeLayout)itemView.findViewById(R.id.icon_container);
            iconBack = (RelativeLayout) itemView.findViewById(R.id.icon_back);
            iconFront = (RelativeLayout) itemView.findViewById(R.id.icon_front);
        }
    }

    @Override
    public LeadItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.lead_item, parent, false);

        return new MyLeadAdapter.LeadItem(itemView);
    }

    @Override
    public void onBindViewHolder(final LeadItem holder, final int position) {

        final LeadEntity leadEntity = LeadEntityList.get(position);

        holder.txtCustom.setText(leadEntity.getCustName());
        holder.txtlead.setText("" + leadEntity.getLeadId());
        holder.txtAmnt.setText("" + leadEntity.getLoanAmnt());

        holder.txtMobile.setText(leadEntity.getMobNo());
        holder.txtStatus.setText(leadEntity.getStatus());


                if(!leadEntity.isSelected())        // defaultChecking
                {
                    leadEntity.setSelected(true);

                    holder.iconFront.setVisibility(View.GONE);
                    resetIconYAxis(holder.iconBack);
                    holder.iconBack.setVisibility(View.VISIBLE);
                    holder.iconBack.setAlpha(1);
                    FlipAnimator.flipView(mContext,holder.iconBack,holder.iconFront,true);

                }else{
                    leadEntity.setSelected(false);

                    holder.iconBack.setVisibility(View.GONE);
                    resetIconYAxis(holder.iconFront);
                    holder.iconFront.setVisibility(View.VISIBLE);
                    holder.iconFront.setAlpha(1);
                    FlipAnimator.flipView(mContext,holder.iconBack,holder.iconFront,false);

                }




        holder.txtlead.setLongClickable(true);

        // region Edit
        holder.lvlead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                View content = inflater.inflate(R.layout.lead_edit_item, null);

                final EditText etCustom = (EditText) content.findViewById(R.id.etCustom);
                final TextView txtlead = (TextView) content.findViewById(R.id.txtlead);
                final EditText etAmnt = (EditText) content.findViewById(R.id.etAmnt);
                final EditText etMobile = (EditText) content.findViewById(R.id.etMobile);
                final EditText etStatus = (EditText) content.findViewById(R.id.etStatus);

                etCustom.setText(leadEntity.getCustName());
                etAmnt.setText(String.valueOf(leadEntity.getLoanAmnt()));
                txtlead.setText(String.valueOf(leadEntity.getLeadId()));
                etMobile.setText(leadEntity.getMobNo().toString());
                etStatus.setText(leadEntity.getStatus().toString());


                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setView(content)
                        .setTitle("Edit Lead")
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {


                                        leadEntity.setCustName(etCustom.getText().toString());
                                        leadEntity.setLoanAmnt(Integer.valueOf(etAmnt.getText().toString()));
                                        leadEntity.setMobNo(etMobile.getText().toString());
                                        leadEntity.setStatus(etStatus.getText().toString());


                                        notifyDataSetChanged();


                            }
                        })

                        .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
         //endregion

        holder.lvlead.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setMessage("Are you sure want to delete " + leadEntity.getCustName())
                        .setTitle("Delete Lead")
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                final String strName = leadEntity.getCustName();

                                        leadEntity.deleteFromRealm();
                                        notifyItemRemoved(position);

                                        Toast.makeText(mContext, strName + " is removed..", Toast.LENGTH_SHORT).show();


                            }
                        })

                        .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();

                return false;
            }
        });

        // displaying the first letter of From in icon text
        holder.iconText.setText(leadEntity.getCustName().substring(0,1));

        holder.imgProfile.setImageResource(R.drawable.bg_circle);
        holder.imgProfile.setColorFilter(leadEntity.getColor());
        //region Apply Icon Animation
           holder.iconContainer.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {

                   holder.itemView.setActivated(true);

                           if(leadEntity.isSelected())        // default value is false...
                           {
                               leadEntity.setSelected(true);

                               holder.iconFront.setVisibility(View.GONE);
                               resetIconYAxis(holder.iconBack);
                               holder.iconBack.setVisibility(View.VISIBLE);
                               holder.iconBack.setAlpha(1);
                               FlipAnimator.flipView(mContext,holder.iconBack,holder.iconFront,true);

                           }else{
                               leadEntity.setSelected(false);

                               holder.iconBack.setVisibility(View.GONE);
                               resetIconYAxis(holder.iconFront);
                               holder.iconFront.setVisibility(View.VISIBLE);
                               holder.iconFront.setAlpha(1);
                               FlipAnimator.flipView(mContext,holder.iconBack,holder.iconFront,false);

                           }
                        //   realm.copyToRealmOrUpdate(leadEntity);
                       }
                   });





        //endregion

    }

    private void resetIconYAxis(View view) {
        if (view.getRotationY() != 0) {
            view.setRotationY(0);
        }
    }

    @Override
    public int getItemCount() {
        return LeadEntityList.size();
    }


    public void findAll(List<LeadEntity> lstList) {
        LeadEntityList = lstList;
        notifyDataSetChanged();
    }
}
