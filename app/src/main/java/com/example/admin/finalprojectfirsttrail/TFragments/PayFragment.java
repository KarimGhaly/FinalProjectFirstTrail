package com.example.admin.finalprojectfirsttrail.TFragments;


import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.finalprojectfirsttrail.FragmentClass.PayStubFragClass;
import com.example.admin.finalprojectfirsttrail.InfoClass.AdvanceInfoClass;
import com.example.admin.finalprojectfirsttrail.R;
import com.example.admin.finalprojectfirsttrail.RecyclerViewApadpters.PaySlipViewPagerAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.admin.finalprojectfirsttrail.InfoClass.*;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Date;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class PayFragment extends Fragment {
    public static final String TAG = "PayFragmentTAG";
    private static final int GALLERY_INTENT = 12;

    PayStubFragClass payStubFragClass;
    @BindView(R.id.payStubTitle)
    TextView payStubTitle;
    @BindView(R.id.btnPayStubFrag_advancesRequested)
    Button btnPayStubFragAdvancesRequested;
    @BindView(R.id.btnPayStubFrag_requestAdvance)
    Button btnPayStubFragRequestAdvance;
    @BindView(R.id.lowButtonFrame)
    LinearLayout lowButtonFrame;
    @BindView(R.id.btnPayStubFrag_expenseReport)
    Button btnPayStubFragExpenseReport;
    @BindView(R.id.btnPayStubFrag_submitExpense)
    Button btnPayStubFragSubmitExpense;
    @BindView(R.id.highButtonFrame)
    LinearLayout highButtonFrame;
    @BindView(R.id.financesViewpager)
    ViewPager financesViewpager;
    @BindView(R.id.clPayStubFragment)
    ConstraintLayout clPayStubFragment;
    Unbinder unbinder;

    FirebaseDatabase database;
    DatabaseReference ref;
    private String uid;
    private int gallery_intent;


    public PayFragment() {
        // Required empty public constructor
    }

    public PayFragment(PayStubFragClass payStubFragClass) {
        this.payStubFragClass = payStubFragClass;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser User = auth.getCurrentUser();
        if (User != null) {
            uid = User.getUid();
            database = FirebaseDatabase.getInstance();
            ref = database.getReference("Consultants_Records").child(uid).child("Training Phase").child("Finance");
        }


        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pay, container, false);
        unbinder = ButterKnife.bind(this, view);
        financesViewpager.setAdapter(new PaySlipViewPagerAdapter(getContext(), payStubFragClass.getPaySlips()));

        return view;
    }

    public void RequestAdvance() {
        final Dialog RADialog = new Dialog(getContext());
        RADialog.setTitle("Request Advance");
        RADialog.setContentView(R.layout.alert_dialog_request_advance);
        final EditText amount = RADialog.findViewById(R.id.tvRequestAdvance_amount);
        final EditText desc = RADialog.findViewById(R.id.tvRequestAdvance_description);
        Button Submit = RADialog.findViewById(R.id.btnSubmitRequestAdvance);
        Button Cancel = RADialog.findViewById(R.id.btnCancelAlertDialog);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdvanceInfoClass advance = new AdvanceInfoClass();
                advance.setAmount(Float.valueOf(amount.getText().toString()));
                advance.setDescriction(desc.getText().toString());
                advance.setStatus("Pending");
                advance.setDate(new Date());
                ref.child("Advance").push().setValue(advance, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        Toast.makeText(getContext(), "Advance Added Successfully", Toast.LENGTH_SHORT).show();
                        RADialog.dismiss();
                    }
                });

            }
        });
        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RADialog.dismiss();
            }
        });
        RADialog.show();

    }

    //////////////////////////////////////////////////////////////NEW
    public void SubmitExpense() {
        final Dialog SEDialog = new Dialog(getContext());
        SEDialog.setTitle("Submit Expense");
        SEDialog.setContentView(R.layout.alert_dialog_expense);

        final EditText etAmount = SEDialog.findViewById(R.id.etSubmitExpense_amount);
        final EditText etDescription = SEDialog.findViewById(R.id.etSubmitExpense_description);
        final Button btnUploadReceipt = SEDialog.findViewById(R.id.btnSubmitExpense_uploadReceipt);
        final TextView photoUrl = SEDialog.findViewById(R.id.tvSubmitExpense_photoUrl);
        final Button btnSubmitExpense = SEDialog.findViewById(R.id.btnSubmitExpense_submitExpense);
        final Button btnCancel = SEDialog.findViewById(R.id.btnCancelAlertDialog);
        final boolean[] flag = {false};

        btnUploadReceipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag[0] = true;

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, GALLERY_INTENT);
            }
        });
        btnSubmitExpense.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                if (!Objects.equals(etAmount.getText().toString(), "")
                        && !Objects.equals(etDescription.getText().toString(), "")) {
                    if (flag[0]) {
                        ExpenseInfoClass expense = new ExpenseInfoClass();
                        expense.setAmount(Float.valueOf(etAmount.getText().toString()));
                        expense.setDescription(etDescription.getText().toString());
                    } else {
                        Toast.makeText(getContext(), "Insert Receipt", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SEDialog.dismiss();
            }
        });
        SEDialog.show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.btnPayStubFrag_advancesRequested, R.id.btnPayStubFrag_requestAdvance, R.id.btnPayStubFrag_expenseReport, R.id.btnPayStubFrag_submitExpense})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnPayStubFrag_advancesRequested:
                break;
            case R.id.btnPayStubFrag_requestAdvance:
                RequestAdvance();
                break;
            case R.id.btnPayStubFrag_expenseReport:
                SubmitExpense();//////////////NEW
                break;
            case R.id.btnPayStubFrag_submitExpense:
                break;
        }
    }

    ////////////NEW
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GALLERY_INTENT && resultCode == RESULT_OK){
            Uri uri = data.getData();

            StorageReference mStorageRef = FirebaseStorage.getInstance().getReference();
            StorageReference filepath = mStorageRef.child("Photos").child(uri.getLastPathSegment());
            //StorageReference filepath = mStorageRef.child("Edel").child(uri.getPath());

            filepath.putFile(uri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(getContext(), "Upload Successful", Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getContext(), "Upload Unsuccessful", Toast.LENGTH_LONG).show();
                            Log.d(TAG, "onFailure: "+ e);
                        }
                    });
        }
    }
}
