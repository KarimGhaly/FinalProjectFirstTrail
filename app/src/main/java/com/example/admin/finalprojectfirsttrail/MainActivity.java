package com.example.admin.finalprojectfirsttrail;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.admin.finalprojectfirsttrail.FragmentClass.AccountFragClass;
import com.example.admin.finalprojectfirsttrail.FragmentClass.BenfitiesFragClass;
import com.example.admin.finalprojectfirsttrail.FragmentClass.InterviewFragClass;
import com.example.admin.finalprojectfirsttrail.FragmentClass.PayStubFragClass;
import com.example.admin.finalprojectfirsttrail.FragmentClass.TrainingFragClass;
import com.example.admin.finalprojectfirsttrail.InfoClass.ConsultantInfoClass;
import com.example.admin.finalprojectfirsttrail.InfoClass.ContactInfoClass;
import com.example.admin.finalprojectfirsttrail.InfoClass.GradedAssignmentInfoClass;
import com.example.admin.finalprojectfirsttrail.InfoClass.HousingInfoClass;
import com.example.admin.finalprojectfirsttrail.InfoClass.InsuranceInfoClass;
import com.example.admin.finalprojectfirsttrail.InfoClass.InterviewInfoClass;
import com.example.admin.finalprojectfirsttrail.InfoClass.PTOInfoClass;
import com.example.admin.finalprojectfirsttrail.InfoClass.PaySlipInfoClass;
import com.example.admin.finalprojectfirsttrail.InfoClass.TeamBindingClass;
import com.example.admin.finalprojectfirsttrail.InfoClass.TodayAssigmentInfoClass;
import com.example.admin.finalprojectfirsttrail.TFragments.AccountFragment;
import com.example.admin.finalprojectfirsttrail.TFragments.BenefitsFragment;
import com.example.admin.finalprojectfirsttrail.TFragments.MarketingFragment;
import com.example.admin.finalprojectfirsttrail.TFragments.PayFragment;
import com.example.admin.finalprojectfirsttrail.TFragments.TrainingFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivityTAG";
    private String uid;
    private DatabaseReference ref;
    private DatabaseReference InfoRef;
    private FirebaseDatabase database;
    FragmentTransaction fragmentTransaction;
    FragmentManager fragmentManager;


    AccountFragment accountFrag;
    PayFragment payFrag;
    BenefitsFragment benefitsFrag;
    TrainingFragment trainingFrag;
    MarketingFragment marketingFrag;
    int tabOpen=1;


    AccountFragClass accountFragClass;
    PayStubFragClass payStubFragClass;
    BenfitiesFragClass benfitiesFragClass;
    TrainingFragClass trainingFragClass;
    InterviewFragClass interviewFragClass;
    private float sum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Consultants_Records");
        InfoRef = database.getReference();

        if (user != null) {
            uid = user.getUid();
        }
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "onSaveInstanceState: ");
        List<Fragment> fragmentList = fragmentManager.getFragments();
        for(Fragment frag : fragmentList)
        {
            if(frag!=null )
            fragmentTransaction.remove(frag).commit();
        }

        super.onSaveInstanceState(outState);
        outState.putInt("tabOpen", tabOpen);

    }

    @Override
    protected void onResume() {
        super.onResume();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        openTab(tabOpen);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "onRestoreInstanceState: ");
        if (savedInstanceState!=null){
            tabOpen = savedInstanceState.getInt("tabOpen");
        }
    }

    private void openTab(int tabid)
    {
        switch (tabid)
        {
            case 1: {
                if (accountFragClass == null) {
                    Log.d(TAG, "openTab: First Time");
                    setAccountFlagInfo();
                } else {
                    Log.d(TAG, "openTab: Not First Time");
                    getSupportFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.slide_in,R.anim.slide_out)
                            .replace(R.id.content, accountFrag, "frag").commit();
                }
                break;
            }
            case 2:
                if(payStubFragClass==null)
                {
                    Log.d(TAG, "openTab: First Time");
                    getPaySlips();
                }
                else {
                    Log.d(TAG, "openTab: Not First");
                    getSupportFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.slide_in,R.anim.slide_out)
                            .replace(R.id.content, payFrag, "frag")
                            .commit();
                }
                break;
            case 3:
                if(benfitiesFragClass == null) {
                    Log.d(TAG, "openTab: First Time");
                    getBenfits();
                }
                else {
                    Log.d(TAG, "openTab: Not First");
                    getSupportFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.slide_in,R.anim.slide_out)
                            .replace(R.id.content, benefitsFrag, "frag").commit();
                }
                break;
            case 4:
                if(trainingFragClass==null)
                {
                    Log.d(TAG, "openTab: First");
                    getTrainingData();
                }
                else {
                    Log.d(TAG, "openTab: Not First");
                    getSupportFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.slide_in,R.anim.slide_out)
                            .replace(R.id.content, trainingFrag, "frag").commit();
                }
                break;
            case 5:
                if(interviewFragClass==null)
                {
                    Log.d(TAG, "openTab: First");
                    getInterviewList();
                }
                else
                {
                    Log.d(TAG, "openTab: Not First");
                    getSupportFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.slide_in,R.anim.slide_out)
                            .replace(R.id.content, marketingFrag, "frag").commit();
                }
                break;
        }
    }




    private void setAccountFlagInfo() {
        ref.child(uid).child("Training Phase").child("Account").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                accountFragClass = new AccountFragClass();
                String Consultant_Ref = dataSnapshot.child("Consultant_Refrence").getValue(String.class);
                getConsultantInfo(Consultant_Ref);
                String Housing_Ref = dataSnapshot.child("Housing_Refrence").getValue(String.class);
                getHousingInfo(Housing_Ref);
                String Instuctor_Ref = dataSnapshot.child("Instructor_Refrence").getValue(String.class);
                getContactInfo(Instuctor_Ref,"Insturcotr");
                String TrainingManager_Ref = dataSnapshot.child("TrainingManager_Refrence").getValue(String.class);
                getContactInfo(TrainingManager_Ref,"Training_Manager");
                String MarketingManager_Ref = dataSnapshot.child("MarketingManager_Refrence").getValue(String.class);
                getContactInfo(MarketingManager_Ref,"Marketing_Manager");
                String Team_Refrence = dataSnapshot.child("Team_Refrence").getValue(String.class);
                BuildTeamInfoClass(Team_Refrence);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "onCancelled: ");
            }
        });
    }

    private  void getConsultantInfo(String Consultant_Refrence){
        InfoRef.child("Consultant_Information").child(Consultant_Refrence).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                accountFragClass.setConsultantInfoClass(dataSnapshot.getValue(ConsultantInfoClass.class));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void getHousingInfo(String housing_ref) {
        InfoRef.child("Housing_Information").child(housing_ref).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                accountFragClass.setHousingInfoClass(dataSnapshot.getValue(HousingInfoClass.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void getContactInfo(String Contact_Ref, final String Position) {
        InfoRef.child("Managers_Info").child(Contact_Ref).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                switch (Position)
                {
                    case "Insturcotr":
                        accountFragClass.setInstuctor_Contact(dataSnapshot.getValue(ContactInfoClass.class));
                        break;
                    case "Training_Manager":
                        accountFragClass.setTrainingManager_Contact(dataSnapshot.getValue(ContactInfoClass.class));
                        break;
                    case "Marketing_Manager":
                        accountFragClass.setMarketingManager_Contact(dataSnapshot.getValue(ContactInfoClass.class));
                        break;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void BuildTeamInfoClass(String Team_Refrence)
    {
        final String Team_Ref = Team_Refrence;
        InfoRef.child("Teams_Info").child(Team_Ref).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                accountFragClass.teamInfoClass.setTeam_Name(dataSnapshot.getValue(String.class));
                InfoRef.child("Data_Binding").child("Teams_Consult").orderByChild("teamRefrence").equalTo(Team_Ref).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot D : dataSnapshot.getChildren())
                        {
                            InfoRef.child("Consultant_Information").child(D.getValue(TeamBindingClass.class).getConsultantRefrence()).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    ConsultantInfoClass conusltant = dataSnapshot.getValue(ConsultantInfoClass.class);
                                    accountFragClass.teamInfoClass.addTeam_Members(conusltant.getFirstName() + "  " + conusltant.getLastName());
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                Log.d(TAG, "onDataChange: ");
                CreateAccountFragment();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void CreateAccountFragment() {
        Log.d(TAG, "CreateAccountFragment: ");
        accountFrag = new AccountFragment(accountFragClass);
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_in,R.anim.slide_out)
                .replace(R.id.content, accountFrag, "frag").commit();
    }


    private void getPaySlips(){
        payStubFragClass = new PayStubFragClass();
        ref.child(uid).child("Training Phase").child("Finance").child("Pay Slips").orderByKey().limitToLast(10).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               List<PaySlipInfoClass> paysList = new ArrayList<PaySlipInfoClass>();
                for(DataSnapshot D : dataSnapshot.getChildren())
                {
                    paysList.add(D.getValue(PaySlipInfoClass.class));
                }
                Collections.reverse(paysList);
                payStubFragClass.setPaySlips(paysList);
                CreatePayFragment();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });


    }

    private void CreatePayFragment(){
        payFrag = new PayFragment(payStubFragClass);
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_in,R.anim.slide_out)
                .replace(R.id.content,payFrag, "frag").commit();
    }

    private void getBenfits(){
        benfitiesFragClass = new BenfitiesFragClass();
        ref.child(uid).child("Training Phase").child("Benefits").child("PTO").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                benfitiesFragClass.setPtoInfoClass(dataSnapshot.getValue(PTOInfoClass.class));
                ref.child(uid).child("Training Phase").child("Benefits").child("PTO").removeEventListener(this);
                getInsuranceList();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void getInsuranceList() {
        ref.child(uid).child("Training Phase").child("Benefits").child("Insurance").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot D: dataSnapshot.getChildren())
                {
                    benfitiesFragClass.addtoInsuranceClass(D.getValue(InsuranceInfoClass.class));
                }
                CreateBenfitsFragment();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void CreateBenfitsFragment()
    {
        benefitsFrag = new BenefitsFragment(benfitiesFragClass);
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_in,R.anim.slide_out)
                .replace(R.id.content, benefitsFrag, "frag").commit();
    }

    private void getTrainingData() {
        trainingFragClass = new TrainingFragClass();
        ref.child(uid).child("Training Phase").child("Training").child("Assignments").orderByKey().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<TodayAssigmentInfoClass> assignemtList = new ArrayList<TodayAssigmentInfoClass>();
                for(DataSnapshot D: dataSnapshot.getChildren())
                {
                    assignemtList.add(D.getValue(TodayAssigmentInfoClass.class));
                }
                Collections.reverse(assignemtList);
                trainingFragClass.setTodayAssigmentsList(assignemtList);
                getTrainingGrades();
                ref.child(uid).child("Training Phase").child("Training").child("Assignments").removeEventListener(this);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void getTrainingGrades() {
        sum = 0;
        ref.child(uid).child("Training Phase").child("Training").child("Grades").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                trainingFragClass.clearList();
                for(DataSnapshot D: dataSnapshot.getChildren())
                {
                    GradedAssignmentInfoClass grade = D.getValue(GradedAssignmentInfoClass.class);
                    sum += grade.getGrade();
                    trainingFragClass.addGradeList(grade);
                }
                trainingFragClass.setOverallGrade((sum/dataSnapshot.getChildrenCount()) * 100);
                CreateTrainingFragment();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void CreateTrainingFragment()
    {
        trainingFrag = new TrainingFragment(trainingFragClass);
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_in,R.anim.slide_out)
                .replace(R.id.content, trainingFrag, "frag").commit();
    }

    private void getInterviewList() {
        interviewFragClass = new InterviewFragClass();
        ref.child(uid).child("Training Phase").child("Interviews").child("Upcoming Interviews").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                interviewFragClass.clearList();
                for(DataSnapshot D: dataSnapshot.getChildren())
                {
                    interviewFragClass.addtoInterviewList(D.getValue(InterviewInfoClass.class));
                }
                CreateMarketFragment();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    private void CreateMarketFragment(){
        marketingFrag = new MarketingFragment(interviewFragClass);
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_in,R.anim.slide_out)
                .replace(R.id.content, marketingFrag, "frag").commit();

    }


    BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_account:
                     tabOpen = 1;
                    break;
                case R.id.navigation_pay:
                    tabOpen = 2;
                    break;
                case R.id.navigation_benefits:
                    tabOpen =3;
                    break;
                case R.id.navigation_training:
                    tabOpen=4;
                    break;
                case R.id.navigation_marketing:
                    tabOpen=5;
                    break;
            }
            openTab(tabOpen);
            return true;
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(tabOpen == 2) {
            payFrag.onActivityResult(requestCode,resultCode,data);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}
