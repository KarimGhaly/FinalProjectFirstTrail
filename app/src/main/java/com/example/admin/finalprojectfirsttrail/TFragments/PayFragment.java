package com.example.admin.finalprojectfirsttrail.TFragments;


import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.finalprojectfirsttrail.FragmentClass.PayStubFragClass;
import com.example.admin.finalprojectfirsttrail.InfoClass.AdvanceInfoClass;
import com.example.admin.finalprojectfirsttrail.InfoClass.ExpenseInfoClass;
import com.example.admin.finalprojectfirsttrail.R;
import com.example.admin.finalprojectfirsttrail.RecyclerViewApadpters.AdvanceRecyclerAdapter;
import com.example.admin.finalprojectfirsttrail.RecyclerViewApadpters.PaySlipViewPagerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class PayFragment extends Fragment implements AdvanceRecyclerAdapter.RecyclerViewInterface {
    public static final String TAG = "PayFragmentTAG";
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

    RecyclerView recyclerView;

    FirebaseDatabase database;
    DatabaseReference ref;
    private String uid;

    private AdvanceRecyclerAdapter advanceRecyclerAdapter;
    private Boolean haveImage;
    private  int imageUploaded = 0;
    private String PhotoURL;
    private Dialog seDialog;
    private List<AdvanceInfoClass> advanceInfoClasses;
    private CheckBox cbUpdateExpense_haveReceipt;
    private Dialog updateExpenseDialog;
    private List<ExpenseInfoClass> expensesList;

    public PayFragment() {
        // Required empty public constructor
    }

    public PayFragment(PayStubFragClass payStubFragClass) {
        this.payStubFragClass = payStubFragClass;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser User = auth.getCurrentUser();
        if (User != null) {
            uid = User.getUid();
            database = FirebaseDatabase.getInstance();
            ref = database.getReference("Consultants_Records").child(uid).child("Training Phase").child("Finance");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pay, container, false);
        unbinder = ButterKnife.bind(this, view);
        financesViewpager.setAdapter(new PaySlipViewPagerAdapter(getContext(), payStubFragClass.getPaySlips()));

        return view;
    }

    public void SubmitExpense(){
        seDialog = new Dialog(getContext());
        seDialog.setTitle("Submit Expense");
        seDialog.setContentView(R.layout.alert_dialog_expense);
        final EditText etAmount = seDialog.findViewById(R.id.etSubmitExpense_amount);
        final EditText etDescription = seDialog.findViewById(R.id.etSubmitExpense_description);
        final Button btnUploadReceipt = seDialog.findViewById(R.id.btnSubmitExpense_uploadReceipt);
        final Button btnSubmitExpense = seDialog.findViewById(R.id.btnSubmitExpense_submitExpense);
        final Button btnCancel = seDialog.findViewById(R.id.btnCancelAlertDialog);

        btnUploadReceipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                haveImage = true;
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                getActivity().startActivityForResult(intent,555);
            }
        });
        btnSubmitExpense.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (! etAmount.getText().toString().isEmpty() && ! etDescription.getText().toString().isEmpty()) {
                    ExpenseInfoClass expense = new ExpenseInfoClass();
                    expense.setAmount(Float.valueOf(etAmount.getText().toString()));
                    expense.setDescription(etDescription.getText().toString());
                    expense.setStatus("Pending");
                    expense.setDate(new Date());
                    if(haveImage)
                    {
                        if(imageUploaded == 1)
                        {
                            if(PhotoURL!= null)
                            {
                                expense.setPhotoUrl(PhotoURL);
                            }
                            insertExpenseToDB(expense);
                        }
                        else if (imageUploaded == 2)
                        {
                            Toast.makeText(getContext(), "Image Failed to Upload", Toast.LENGTH_SHORT).show();
                        }
                        else if(imageUploaded == 3)
                        {
                            Toast.makeText(getActivity(), "Image Canceled", Toast.LENGTH_SHORT).show();
                            expense.setPhotoUrl(null);
                            insertExpenseToDB(expense);
                        }
                        else
                        {
                            Toast.makeText(getActivity(), "Please Wait Image to Finish Uploading", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        expense.setPhotoUrl(null);
                        insertExpenseToDB(expense);
                    }

                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seDialog.dismiss();
            }
        });
        seDialog.show();

    }

    public void insertExpenseToDB(ExpenseInfoClass expense)
    {
        ref.child("Expenses").push().setValue(expense, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if(databaseError == null) {
                    Toast.makeText(getContext(), "Expense Submited Successfully", Toast.LENGTH_SHORT).show();
                    seDialog.dismiss();
                }
                else
                {
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onComplete: "+databaseError.getMessage());
                }
            }
        });
    }

    public void RequestAdvance() {
        final Dialog RADialog = new Dialog(getContext());
        RADialog.setTitle("Request Advance");
        RADialog.setContentView(R.layout.alert_dialog_request_advance);
        final EditText amount = RADialog.findViewById(R.id.tvRequestAdvance_amount);
        final EditText desc = RADialog.findViewById(R.id.tvRequestAdvance_description);
        Button Submit = RADialog.findViewById(R.id.btnSubmitRequestAdvance);
        Button Cancle = RADialog.findViewById(R.id.btnCancelAlertDialog);
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
        Cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RADialog.dismiss();
            }
        });
        RADialog.show();

    }

    public void getRequests(final boolean expense){
        String Child;
        if(expense)
        {
           Child = "Expenses" ;
        }
        else
        {
            Child = "Advance";
        }
        ref.child(Child).orderByKey().limitToLast(10).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(expense) {
                    expensesList = new ArrayList<ExpenseInfoClass>();
                    for (DataSnapshot D : dataSnapshot.getChildren()) {
                        ExpenseInfoClass expense = D.getValue(ExpenseInfoClass.class);
                        expense.setKey(D.getKey());
                        expensesList.add(expense);
                    }
                    Collections.reverse(expensesList);
                    advanceRecyclerAdapter = new AdvanceRecyclerAdapter(null, expensesList,PayFragment.this);
                }
                else
                {
                    advanceInfoClasses = new ArrayList<AdvanceInfoClass>();
                    for (DataSnapshot D : dataSnapshot.getChildren()) {
                        AdvanceInfoClass advance = D.getValue(AdvanceInfoClass.class);
                        advance.setKey(D.getKey());
                        advanceInfoClasses.add(advance);
                    }
                    Collections.reverse(advanceInfoClasses);
                    advanceRecyclerAdapter = new AdvanceRecyclerAdapter(advanceInfoClasses,null,PayFragment.this);
                }
                recyclerView.setAdapter(advanceRecyclerAdapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void ShowDialog(boolean expense) {
        final Dialog dilaog = new Dialog(getContext());
        dilaog.setContentView(R.layout.alert_dialog_recyclerview);
        recyclerView = dilaog.findViewById(R.id.RecyclerViewViewAdvances);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Button CloseBTN = dilaog.findViewById(R.id.alertDialogCloseBTN);
        CloseBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dilaog.dismiss();
            }
        });
        getRequests(expense);
        dilaog.show();
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
                ShowDialog(false);
                break;
            case R.id.btnPayStubFrag_requestAdvance:
                RequestAdvance();
                break;
            case R.id.btnPayStubFrag_expenseReport:
                ShowDialog(true);
                break;
            case R.id.btnPayStubFrag_submitExpense:
                haveImage = false;
                imageUploaded = 0;
                PhotoURL = null;
                SubmitExpense();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 555 && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            StorageReference mStorageRef = FirebaseStorage.getInstance().getReference("Photos");
            StorageReference filepath = mStorageRef.child("Expenses").child(uid).child(uri.getLastPathSegment());
            filepath.putFile(uri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(getActivity(), "Upload Successful", Toast.LENGTH_LONG).show();
                            PhotoURL = taskSnapshot.getDownloadUrl().toString();
                            imageUploaded =1;
                            if(cbUpdateExpense_haveReceipt != null)
                            {
                                cbUpdateExpense_haveReceipt.setChecked(true);
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getActivity(), "Upload Unsuccessful", Toast.LENGTH_LONG).show();
                            Log.d(TAG, "onFailure: " + e);
                            imageUploaded = 2;
                        }
                    });
        }
        else
        {
            imageUploaded =3;
        }
    }

    @Override
    public void updateRecord(final AdvanceInfoClass advanceInfoClass) {
        final Dialog updateAdvanceDialog = new Dialog(getContext());
        updateAdvanceDialog.setTitle("Update Advance");
        updateAdvanceDialog.setContentView(R.layout.alert_dialog_update_advance);
        final EditText tvUpdateAdvance_amount = updateAdvanceDialog.findViewById(R.id.tvUpdateAdvance_amount);
        final EditText tvUpdateAdvance_desc = updateAdvanceDialog.findViewById(R.id.tvUpdateAdvance_description);
        Button btnUpdateSubmitBTN = updateAdvanceDialog.findViewById(R.id.btnUpdateRequestAdvance);
        Button btnUpdateCancleBTN = updateAdvanceDialog.findViewById(R.id.btnCancelUpdateAlertDialog);
        Button btnUpdateDeleteBNT = updateAdvanceDialog.findViewById(R.id.btnDeleteUpdateAlertDialog);
        tvUpdateAdvance_amount.setText(String.valueOf(advanceInfoClass.getAmount()));
        tvUpdateAdvance_desc.setText(advanceInfoClass.getDescriction());
        btnUpdateCancleBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAdvanceDialog.dismiss();
            }
        });

        btnUpdateDeleteBNT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref.child("Advance").child(advanceInfoClass.getKey()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getContext(), "Advance Deleted Successfully", Toast.LENGTH_SHORT).show();
                        advanceInfoClasses.remove(advanceInfoClass);
                        advanceRecyclerAdapter.notifyDataSetChanged();
                        updateAdvanceDialog.dismiss();
                    }
                });
            }
        });

        btnUpdateSubmitBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!tvUpdateAdvance_amount.getText().toString().isEmpty()) {
                    AdvanceInfoClass advance = new AdvanceInfoClass();
                    advance.setAmount(Float.valueOf(tvUpdateAdvance_amount.getText().toString()));
                    advance.setDescriction(tvUpdateAdvance_desc.getText().toString());
                    advance.setStatus("Pending");
                    advance.setDate(advanceInfoClass.getDate());
                    ref.child("Advance").child(advanceInfoClass.getKey()).setValue(advance, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                            if (databaseError == null) {
                                Toast.makeText(getContext(), "Advance Updated Successfully", Toast.LENGTH_SHORT).show();
                                updateAdvanceDialog.dismiss();
                            } else {
                                Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                                Log.d(TAG, "onComplete: " + databaseError.getMessage());
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(getContext(), "Please Make sure to Enter Amount", Toast.LENGTH_SHORT).show();
                }

            }
        });
        updateAdvanceDialog.show();
    }

    @Override
    public void updateRecord(final ExpenseInfoClass expenseInfoClass) {
        haveImage = false;
        imageUploaded = 0;
        PhotoURL = null;
        updateExpenseDialog = new Dialog(getContext());
        updateExpenseDialog.setTitle("Update Expenses");
        updateExpenseDialog.setContentView(R.layout.alert_dialog_update_expense);
        final EditText etUpdateExpense_amount = updateExpenseDialog.findViewById(R.id.etUpdateExpense_amount);
        final EditText etUpdateExpense_description = updateExpenseDialog.findViewById(R.id.etUpdateExpense_description);
        cbUpdateExpense_haveReceipt = updateExpenseDialog.findViewById(R.id.cbUpdateExpense_haveReceipt);
        Button btnUpdateExpense_uploadReceipt = updateExpenseDialog.findViewById(R.id.btnUpdateExpense_uploadReceipt);
        Button btnUpdateExpense_submitExpense = updateExpenseDialog.findViewById(R.id.btnUpdateExpense_submitExpense);
        Button btnUpdateExpense_CancelAlertDialog = updateExpenseDialog.findViewById(R.id.btnUpdateExpense_CancelAlertDialog);
        Button btnUpdateExpense_Delete = updateExpenseDialog.findViewById(R.id.btnUpdateExpense_Delete);

        etUpdateExpense_amount.setText(String.valueOf(expenseInfoClass.getAmount()));
        etUpdateExpense_description.setText(expenseInfoClass.getDescription());
        if(expenseInfoClass.getPhotoUrl()!= null)
        {
            cbUpdateExpense_haveReceipt.setChecked(true);
            haveImage = true;
            imageUploaded = 1;
            PhotoURL = expenseInfoClass.getPhotoUrl();
        }
        else
        {
            cbUpdateExpense_haveReceipt.setChecked(false);
        }

        btnUpdateExpense_CancelAlertDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateExpenseDialog.dismiss();
            }
        });

        btnUpdateExpense_uploadReceipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                haveImage=true;
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                getActivity().startActivityForResult(intent,555);
            }
        });
        btnUpdateExpense_submitExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (! etUpdateExpense_amount.getText().toString().isEmpty() && ! etUpdateExpense_description.getText().toString().isEmpty()) {
                    ExpenseInfoClass expense = new ExpenseInfoClass();
                    expense.setAmount(Float.valueOf(etUpdateExpense_amount.getText().toString()));
                    expense.setDescription(etUpdateExpense_description.getText().toString());
                    expense.setStatus("Pending");
                    expense.setDate(expenseInfoClass.getDate());
                    if(haveImage)
                    {
                        if(imageUploaded == 1)
                        {
                            if(PhotoURL!= null && cbUpdateExpense_haveReceipt.isChecked())
                            {
                                expense.setPhotoUrl(PhotoURL);
                            }
                            updateExpenseToDB(expense,expenseInfoClass.getKey());
                        }
                        else if (imageUploaded == 2)
                        {
                            Toast.makeText(getContext(), "Image Failed to Upload", Toast.LENGTH_SHORT).show();
                        }
                        else if(imageUploaded == 3)
                        {
                            Toast.makeText(getActivity(), "Image Canceled", Toast.LENGTH_SHORT).show();
                            expense.setPhotoUrl(null);
                            updateExpenseToDB(expense,expenseInfoClass.getKey());
                        }
                        else
                        {
                            Toast.makeText(getActivity(), "Please Wait Image to Finish Uploading", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        expense.setPhotoUrl(null);
                        updateExpenseToDB(expense,expenseInfoClass.getKey());
                    }

                }
            }
        });
        btnUpdateExpense_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref.child("Expenses").child(expenseInfoClass.getKey()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getContext(), "Expense Deleted Successfully", Toast.LENGTH_SHORT).show();
                        expensesList.remove(expenseInfoClass);
                        advanceRecyclerAdapter.notifyDataSetChanged();
                        updateExpenseDialog.dismiss();
                    }
                });
            }
        });
        updateExpenseDialog.show();

    }

    private void updateExpenseToDB(ExpenseInfoClass expense, String key) {
        ref.child("Expenses").child(key).setValue(expense, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if(databaseError == null) {
                    Toast.makeText(getContext(), "Expense Updated Successfully", Toast.LENGTH_SHORT).show();
                    updateExpenseDialog.dismiss();
                }
                else
                {
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onComplete: "+databaseError.getMessage());
                }
            }
        });
    }
}
