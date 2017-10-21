package com.example.admin.finalprojectfirsttrail.TFragments;


import android.app.Dialog;
import android.os.Bundle;
import android.support.constraint.Guideline;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.finalprojectfirsttrail.FragmentClass.BenfitiesFragClass;
import com.example.admin.finalprojectfirsttrail.InfoClass.InsuranceInfoClass;
import com.example.admin.finalprojectfirsttrail.InfoClass.PTOInfoClass;
import com.example.admin.finalprojectfirsttrail.InfoClass.PTORequestInfoClass;
import com.example.admin.finalprojectfirsttrail.R;
import com.example.admin.finalprojectfirsttrail.RecyclerViewApadpters.InsuranceRecyclerView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class BenefitsFragment extends Fragment {
    public static final String TAG = "BenefitsFragmentTAG";

    FirebaseDatabase database;
    DatabaseReference ref;
    private String uid;

    BenfitiesFragClass benfitiesFragClass;

    @BindView(R.id.tvPto_totalPtoTitle)
    TextView tvPtoTotalPtoTitle;
    @BindView(R.id.tvPto_totalPto)
    TextView tvPtoTotalPto;
    @BindView(R.id.tvPto_usedPtoTitle)
    TextView tvPtoUsedPtoTitle;
    @BindView(R.id.tvPto_usedPto)
    TextView tvPtoUsedPto;
    @BindView(R.id.tvPto_remainingPtoTitle)
    TextView tvPtoRemainingPtoTitle;
    @BindView(R.id.tvPto_remainingPto)
    TextView tvPtoRemainingPto;
    @BindView(R.id.tvPto_btnViewRequestedPto)
    Button tvPtoBtnViewRequestedPto;
    @BindView(R.id.tvPto_btnViewUsedPto)
    Button tvPtoBtnViewUsedPto;
    @BindView(R.id.tvPto_btnRequestPto)
    Button tvPtoBtnRequestPto;
    @BindView(R.id.RVInsurance_benefits)
    RecyclerView RVInsuranceBenefits;
    Unbinder unbinder;
    @BindView(R.id.guidelineA)
    Guideline guidelineA;
    @BindView(R.id.guidelineB)
    Guideline guidelineB;

    private Date date;
    private EditText tvRequestPtoDateFrom;
    private EditText tvRequestPtoDateTo;

    public BenefitsFragment() {
        // Required empty public constructor
    }

    public BenefitsFragment(BenfitiesFragClass benfitiesFragClass) {
        this.benfitiesFragClass = benfitiesFragClass;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_benefits, container, false);
        unbinder = ButterKnife.bind(this, view);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser User = auth.getCurrentUser();
        if (User != null) {
            uid = User.getUid();
            database = FirebaseDatabase.getInstance();
            ref = database.getReference("Consultants_Records").child(uid).child("Training Phase").child("Benefits");
        }

        setupPTOUI(benfitiesFragClass.getPtoInfoClass());
        setupIsuranceUI(benfitiesFragClass.getInsuranceList());
        return view;
    }

    public void setupPTOUI(PTOInfoClass pto) {
        if(pto!=null) {
            tvPtoTotalPto.setText(String.valueOf(pto.getTotalPTO()));
            tvPtoRemainingPto.setText(String.valueOf(pto.getRemainingPTO()));
            tvPtoUsedPto.setText(String.valueOf(pto.getUsedPTO()));
        }
    }

    public void setupIsuranceUI(List<InsuranceInfoClass> insurnceList)
    {
        Collections.sort(insurnceList, new Comparator<InsuranceInfoClass>() {
            @Override
            public int compare(InsuranceInfoClass o1, InsuranceInfoClass o2) {
                return o1.getOrder() - o2.getOrder();
            }
        });
        InsuranceRecyclerView insuranceAdapter = new InsuranceRecyclerView(insurnceList);
        RVInsuranceBenefits.setAdapter(insuranceAdapter);
        RVInsuranceBenefits.setLayoutManager(new LinearLayoutManager(getContext()));
        RVInsuranceBenefits.setItemAnimator(new DefaultItemAnimator());
    }
    private void getRequestedPTO() {
        ref.child("Requested PTO").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<PTORequestInfoClass> ptoRequestsList = new ArrayList<PTORequestInfoClass>();
                for(DataSnapshot D: dataSnapshot.getChildren())
                {
                    PTORequestInfoClass pto = D.getValue(PTORequestInfoClass.class);
                    pto.setKey(D.getKey());
                    ptoRequestsList.add(pto);
                }
                ShowRecyclerViewDialog(ptoRequestsList);
                ref.child("Requested PTO").removeEventListener(this);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void ShowRecyclerViewDialog(List<PTORequestInfoClass> ptoRequestsList) {

    }

    public void RequestPTO() {
        final Dialog dialog = new Dialog(getContext());
        dialog.setTitle("Request PTO");
        View view = View.inflate(getContext(), R.layout.alert_dialog_request_pto, null);
        dialog.setContentView(view);

        tvRequestPtoDateFrom = dialog.findViewById(R.id.tvRequestPto_dateFrom);
        tvRequestPtoDateTo = dialog.findViewById(R.id.tvRequestPto_dateTo);
        tvRequestPtoDateFrom.setKeyListener(null);
        tvRequestPtoDateTo.setKeyListener(null);
        final EditText tvRequestPtoDescription = dialog.findViewById(R.id.tvRequestPto_description);
        Button btnSubmitRequestPto = dialog.findViewById(R.id.btnSubmitRequestPto);
        Button btnCancelAlertDialog = dialog.findViewById(R.id.btnCancelAlertDialog);
        tvRequestPtoDateFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               ShowDatePickerDialog(new Date(),true);
            }
        });
        tvRequestPtoDateTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
                if(!tvRequestPtoDateFrom.getText().toString().isEmpty()) {
                    try {
                        Date fromDate = simpleDateFormat.parse(tvRequestPtoDateFrom.getText().toString());
                        Calendar fromCalander = Calendar.getInstance();
                        fromCalander.setTime(fromDate);
                        fromCalander.add(Calendar.DAY_OF_MONTH,1);
                        fromDate = fromCalander.getTime();
                        ShowDatePickerDialog(fromDate,false);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                else
                {
                    Toast.makeText(getContext(), "Select Date From First", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnCancelAlertDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnSubmitRequestPto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PTORequestInfoClass ptoRequestInfoClass = new PTORequestInfoClass();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");

                try {
                    ptoRequestInfoClass.setDateFrom(simpleDateFormat.parse(tvRequestPtoDateFrom.getText().toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                try {
                    ptoRequestInfoClass.setDateTo(simpleDateFormat.parse(tvRequestPtoDateTo.getText().toString()));
                } catch (ParseException e) {
                    Log.d(TAG, "onClick: "+e.getMessage());
                    e.printStackTrace();
                }
                ptoRequestInfoClass.setDateRequest(new Date());
                ptoRequestInfoClass.setDescription(tvRequestPtoDescription.getText().toString());
                ptoRequestInfoClass.setStatus("Pending");
                ref.child("Requested PTO").push().setValue(ptoRequestInfoClass, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        if(databaseError == null)
                        {
                            Toast.makeText(getContext(), "PTO Submitted Successfully", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                        else {
                            Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "onComplete: "+databaseError.getMessage());
                        }
                    }
                });
            }
        });
        dialog.show();
    }
    private void ShowDatePickerDialog(Date MinDate, final boolean from)
    {
        final Dialog datepickDialog = new Dialog(getContext());
        datepickDialog.setContentView(R.layout.date_picker_layout);
        final DatePicker datePicker = datepickDialog.findViewById(R.id.datePicker);
        datePicker.setMinDate(MinDate.getTime());
        Button setDate = datepickDialog.findViewById(R.id.setDate);
        setDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                if(from) {
                    tvRequestPtoDateFrom.setText(sdf.format(calendar.getTime()));
                    datepickDialog.dismiss();
                }
                else
                {
                    tvRequestPtoDateTo.setText(sdf.format(calendar.getTime()));
                    datepickDialog.dismiss();
                }

            }
        });
        datepickDialog.show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.tvPto_btnViewRequestedPto)
    public void onTvPtoBtnViewRequestedPtoClicked() {
        getRequestedPTO();
    }



    @OnClick(R.id.tvPto_btnViewUsedPto)
    public void onTvPtoBtnViewUsedPtoClicked() {
    }

    @OnClick(R.id.tvPto_btnRequestPto)
    public void onTvPtoBtnRequestPtoClicked() {
        RequestPTO();
    }

}
