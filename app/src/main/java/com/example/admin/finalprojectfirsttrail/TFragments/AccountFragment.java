package com.example.admin.finalprojectfirsttrail.TFragments;


import android.animation.LayoutTransition;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.transition.Visibility;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.admin.finalprojectfirsttrail.FragmentClass.AccountFragClass;
import com.example.admin.finalprojectfirsttrail.InfoClass.ConsultantInfoClass;
import com.example.admin.finalprojectfirsttrail.InfoClass.ContactInfoClass;
import com.example.admin.finalprojectfirsttrail.InfoClass.HousingInfoClass;
import com.example.admin.finalprojectfirsttrail.InfoClass.TeamInfoClass;
import com.example.admin.finalprojectfirsttrail.R;
import com.example.admin.finalprojectfirsttrail.RecyclerViewApadpters.MainActivity_TeamAdapter;
import com.google.firebase.auth.FirebaseAuth;

import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.example.admin.finalprojectfirsttrail.R.drawable.ic_chevron_right_black_24dp;
import static com.example.admin.finalprojectfirsttrail.R.drawable.ic_expand_more_black_24dp;


public class AccountFragment extends Fragment {


    @BindView(R.id.tvAccountTitle)
    TextView tvAccountTitle;
    @BindView(R.id.ivAccountChevron)
    ImageView ivAccountChevron;
    @BindView(R.id.clAccount)
    ConstraintLayout clAccount;
    @BindView(R.id.tvUserName)
    TextView tvUserName;
    @BindView(R.id.tvUserPosition)
    TextView tvUserPosition;
    @BindView(R.id.ivUserPosition)
    ImageView ivUserPosition;
    @BindView(R.id.tvUserPhone)
    TextView tvUserPhone;
    @BindView(R.id.ivUserPhone)
    ImageView ivUserPhone;
    @BindView(R.id.tvUserSkype)
    TextView tvUserSkype;
    @BindView(R.id.ivUserSkype)
    ImageView ivUserSkype;
    @BindView(R.id.tvUserEmail)
    TextView tvUserEmail;
    @BindView(R.id.ivUserEmail)
    ImageView ivUserEmail;
    @BindView(R.id.tvUserHomeAddress)
    TextView tvUserHomeAddress;
    @BindView(R.id.ivUserHomeAddress)
    ImageView ivUserHomeAddress;
    @BindView(R.id.tvUserEmergencyName)
    TextView tvUserEmergencyName;
    @BindView(R.id.ivUserEmergencyName)
    ImageView ivUserEmergencyName;
    @BindView(R.id.tvUserEmergencyPhone)
    TextView tvUserEmergencyPhone;
    @BindView(R.id.ivUserEmergencyPhone)
    ImageView ivUserEmergencyPhone;
    @BindView(R.id.tvUserJoinDate)
    TextView tvUserJoinDate;
    @BindView(R.id.ivUserJoinDate)
    ImageView ivUserJoinDate;
    @BindView(R.id.svAccounts)
    ScrollView svAccounts;
    @BindView(R.id.cvAccount)
    CardView cvAccount;
    @BindView(R.id.tvUserLodging)
    TextView tvUserLodging;
    @BindView(R.id.ivLodgingChevron)
    ImageView ivLodgingChevron;
    @BindView(R.id.clLodging)
    ConstraintLayout clLodging;
    @BindView(R.id.tvLodgingName)
    TextView tvLodgingName;
    @BindView(R.id.tvLodgingAddress)
    TextView tvLodgingAddress;
    @BindView(R.id.ivLodgingAddress)
    ImageView ivLodgingAddress;
    @BindView(R.id.tvLodgingLockCode)
    TextView tvLodgingLockCode;
    @BindView(R.id.ivLodgingLockCode)
    ImageView ivLodgingLockCode;
    @BindView(R.id.tvLodgingGateCode)
    TextView tvLodgingGateCode;
    @BindView(R.id.ivLodgingGateCode)
    ImageView ivLodgingGateCode;
    @BindView(R.id.tvLodgingBedrooms)
    TextView tvLodgingBedrooms;
    @BindView(R.id.ivLodgingBedrooms)
    ImageView ivLodgingBedrooms;
    @BindView(R.id.tvLodgingBathrooms)
    TextView tvLodgingBathrooms;
    @BindView(R.id.ivLodgingBathrooms)
    ImageView ivLodgingBathrooms;
    @BindView(R.id.tvLodgingBuildingManager)
    TextView tvLodgingBuildingManager;
    @BindView(R.id.ivLodgingBuildingManager)
    ImageView ivLodgingBuildingManager;
    @BindView(R.id.tvLodgingBuildingManagerPhone)
    TextView tvLodgingBuildingManagerPhone;
    @BindView(R.id.ivLodgingBuildingManagerPhone)
    ImageView ivLodgingBuildingManagerPhone;
    @BindView(R.id.svLodging)
    ScrollView svLodging;
    @BindView(R.id.cvLodging)
    CardView cvLodging;
    @BindView(R.id.tvContacts)
    TextView tvContacts;
    @BindView(R.id.ivContactsChevron)
    ImageView ivContactsChevron;
    @BindView(R.id.clContacts)
    ConstraintLayout clContacts;
    @BindView(R.id.tvTrainingManagerPosition)
    TextView tvTrainingManagerPosition;
    @BindView(R.id.tvTrainingManagerName)
    TextView tvTrainingManagerName;
    @BindView(R.id.ivTrainingManagerName)
    ImageView ivTrainingManagerName;
    @BindView(R.id.tvTrainingManagerPhone)
    TextView tvTrainingManagerPhone;
    @BindView(R.id.ivTrainingManagerPhone)
    ImageView ivTrainingManagerPhone;
    @BindView(R.id.tvTrainingManagerSkype)
    TextView tvTrainingManagerSkype;
    @BindView(R.id.ivTrainingManagerSkype)
    ImageView ivTrainingManagerSkype;
    @BindView(R.id.tvTrainingManagerEmail)
    TextView tvTrainingManagerEmail;
    @BindView(R.id.ivTrainingManagerEmail)
    ImageView ivTrainingManagerEmail;
    @BindView(R.id.tvTrainerPosition)
    TextView tvTrainerPosition;
    @BindView(R.id.tvTrainerName)
    TextView tvTrainerName;
    @BindView(R.id.ivTrainerName)
    ImageView ivTrainerName;
    @BindView(R.id.tvTrainerPhone)
    TextView tvTrainerPhone;
    @BindView(R.id.ivTrainerPhone)
    ImageView ivTrainerPhone;
    @BindView(R.id.tvTrainerSkype)
    TextView tvTrainerSkype;
    @BindView(R.id.ivTrainerSkype)
    ImageView ivTrainerSkype;
    @BindView(R.id.tvTrainerEmail)
    TextView tvTrainerEmail;
    @BindView(R.id.ivTrainerEmail)
    ImageView ivTrainerEmail;
    @BindView(R.id.tvMarketingManagerPosition)
    TextView tvMarketingManagerPosition;
    @BindView(R.id.tvMarketingManagerName)
    TextView tvMarketingManagerName;
    @BindView(R.id.ivMarketingManagerName)
    ImageView ivMarketingManagerName;
    @BindView(R.id.tvMarketingManagerPhone)
    TextView tvMarketingManagerPhone;
    @BindView(R.id.ivMarketingManagerPhone)
    ImageView ivMarketingManagerPhone;
    @BindView(R.id.tvMarketingManagerSkype)
    TextView tvMarketingManagerSkype;
    @BindView(R.id.ivMarketingManagerSkype)
    ImageView ivMarketingManagerSkype;
    @BindView(R.id.tvMarketingManagerEmail)
    TextView tvMarketingManagerEmail;
    @BindView(R.id.ivMarketingManagerEmail)
    ImageView ivMarketingManagerEmail;
    @BindView(R.id.svContacts)
    ScrollView svContacts;
    @BindView(R.id.cvContacts)
    CardView cvContacts;
    @BindView(R.id.tvTeam)
    TextView tvTeam;
    @BindView(R.id.ivTeamChevron)
    ImageView ivTeamChevron;
    @BindView(R.id.clTeam)
    ConstraintLayout clTeam;
    @BindView(R.id.rvAccount_TeamList)
    RecyclerView rvAccountTeamList;
    @BindView(R.id.llTeamlist)
    LinearLayout llTeamlist;
    @BindView(R.id.cvTeam)
    CardView cvTeam;
    FirebaseAuth auth;

    Unbinder unbinder;
    @BindView(R.id.tvTeamName)
    TextView tvTeamName;
    private int tabOpen = 0;
    public static final String TAG = "AccountFragmentTAG";
    AccountFragClass accountFragClass;

    public AccountFragment(AccountFragClass accountFragClass) {
        this.accountFragClass = accountFragClass;
    }

    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_account, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        setupUI();
        setConsultantInfoUI(accountFragClass.getConsultantInfoClass());
        setHousingInfoUI(accountFragClass.getHousingInfoClass());
        setContactsUI(accountFragClass.getInstuctor_Contact(), accountFragClass.getTrainingManager_Contact(), accountFragClass.getMarketingManager_Contact());
        setTeamUI(accountFragClass.getTeamInfoClass());
        return rootView;
    }

    private void setupUI() {
        clContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openContacts();
            }
        });
        clAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAccount();
            }
        });
        clLodging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLodging();
            }
        });
        clTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTeam();
            }
        });
        tabOpen = 1;
    }

    private void setConsultantInfoUI(ConsultantInfoClass info) {
        if (info != null) {
            tvUserName.setText(info.getFirstName() + " " + info.getLastName());
            tvUserPosition.setText(info.getTechnology());
            tvUserPhone.setText(info.getPhoneNo());
            tvUserSkype.setText(info.getSkype());
            tvUserEmail.setText(info.getEmail());
            tvUserHomeAddress.setText(info.getAddress());
            tvUserEmergencyName.setText(info.getEmergencyName());
            tvUserEmergencyPhone.setText(info.getEmergencyPhone());
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            tvUserJoinDate.setText(sdf.format(info.getJoinDate()));
        }
    }

    private void setHousingInfoUI(HousingInfoClass info) {
        if (info != null) {
            tvLodgingName.setText(info.getName());
            tvLodgingAddress.setText(info.getAddress());
            tvLodgingGateCode.setText("Gate Code: " + info.getGateCode());
            tvLodgingGateCode.setText("Lock Code: " + info.getLockCode());
            tvLodgingBedrooms.setText(info.getNumBedroom() + " Bedroomds");
            tvLodgingBathrooms.setText(info.getNumBathroom() + " Bathrooms");
            tvLodgingBuildingManager.setText(info.getHouseManager());
            tvLodgingBuildingManagerPhone.setText(info.getBuildingManagerPhone());
        }
    }

    private void setContactsUI(ContactInfoClass instructor, ContactInfoClass trainningManager, ContactInfoClass marketingManager) {
        if (instructor != null) {
            final String IskypeUser = instructor.getSkype();
            tvTrainerName.setText(instructor.getName());
            tvTrainerPhone.setText(instructor.getPhone());
            tvTrainerEmail.setText(instructor.getEmail());
            tvTrainerSkype.setText(IskypeUser);
            tvTrainerSkype.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openSkype(IskypeUser);
                }
            });
        }
        if (trainningManager != null) {
            final String TskypeUser = trainningManager.getSkype();
            tvTrainingManagerName.setText(trainningManager.getName());
            tvTrainingManagerEmail.setText(trainningManager.getEmail());
            tvTrainingManagerPhone.setText(trainningManager.getPhone());
            tvTrainingManagerSkype.setText(TskypeUser);
            tvTrainingManagerSkype.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openSkype(TskypeUser);
                }
            });
        }
        if (marketingManager != null) {
            final String MskypeUser = marketingManager.getSkype();
            tvMarketingManagerName.setText(marketingManager.getName());
            tvMarketingManagerEmail.setText(marketingManager.getEmail());
            tvMarketingManagerPhone.setText(marketingManager.getPhone());
            tvMarketingManagerSkype.setText(MskypeUser);
            tvMarketingManagerSkype.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openSkype(MskypeUser);
                }
            });
        }

    }

    private void setTeamUI(TeamInfoClass team) {
        if (team != null) {
            tvTeamName.setText(team.getTeam_Name());
            MainActivity_TeamAdapter adapter = new MainActivity_TeamAdapter(team.getTeam_Members());
            rvAccountTeamList.setAdapter(adapter);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
            rvAccountTeamList.setLayoutManager(layoutManager);
            rvAccountTeamList.setItemAnimator(itemAnimator);
        }
    }

    public void openSkype(String skypeUser) {

        Context myContext = getContext();
        String mySkypeUri = "Skype:" + skypeUser + "?chat";
        if (!isSkypeClientInstalled(myContext)) {
            goToMarket(myContext);
            return;
        }

        Uri skypeUri = Uri.parse(mySkypeUri);
        Intent myIntent = new Intent(Intent.ACTION_VIEW, skypeUri);
        myIntent.setComponent(new ComponentName("com.skype.raider", "com.skype.raider.Main"));
        myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        myContext.startActivity(myIntent);

        return;

    }

    public boolean isSkypeClientInstalled(Context myContext) {
        PackageManager myPackageMgr = myContext.getPackageManager();
        try {
            myPackageMgr.getPackageInfo("com.skype.raider", PackageManager.GET_ACTIVITIES);
        } catch (PackageManager.NameNotFoundException e) {
            return (false);
        }
        return (true);
    }


    public void goToMarket(Context myContext) {
        Uri marketUri = Uri.parse("market://details?id=com.skype.raider");
        Intent myIntent = new Intent(Intent.ACTION_VIEW, marketUri);
        myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        myContext.startActivity(myIntent);

        return;
    }


    private void openAccount() {
        TranslateAnimation anim = null;

        if (tabOpen != 1) {
            svAccounts.setVisibility(View.VISIBLE);
            ivAccountChevron.setImageDrawable(ContextCompat.getDrawable(getContext(), ic_expand_more_black_24dp));
            svLodging.setVisibility(View.GONE);
            svContacts.setVisibility(View.GONE);
            llTeamlist.setVisibility(View.GONE);
            ivLodgingChevron.setImageDrawable(ContextCompat.getDrawable(getContext(), ic_chevron_right_black_24dp));
            ivContactsChevron.setImageDrawable(ContextCompat.getDrawable(getContext(), ic_chevron_right_black_24dp));
            ivTeamChevron.setImageDrawable(ContextCompat.getDrawable(getContext(), ic_chevron_right_black_24dp));
            tabOpen = 1;
        } else {
            LayoutTransition lt = collapseTransition();
            LayoutTransition lt2 = collapseTransition();
            LayoutTransition lt3 = collapseTransition();

            cvContacts.setLayoutTransition(lt);
            cvTeam.setLayoutTransition(lt2);
            cvLodging.setLayoutTransition(lt3);

            svAccounts.setVisibility(View.GONE);
            ivAccountChevron.setImageDrawable(ContextCompat.getDrawable(getContext(), ic_chevron_right_black_24dp));
            tabOpen = 0;

        }
    }

    private void openLodging() {
        if (tabOpen != 2) {
            svLodging.setVisibility(View.VISIBLE);
            ivLodgingChevron.setImageDrawable(ContextCompat.getDrawable(getContext(), ic_expand_more_black_24dp));
            svAccounts.setVisibility(View.GONE);
            svContacts.setVisibility(View.GONE);
            llTeamlist.setVisibility(View.GONE);
            ivAccountChevron.setImageDrawable(ContextCompat.getDrawable(getContext(), ic_chevron_right_black_24dp));
            ivContactsChevron.setImageDrawable(ContextCompat.getDrawable(getContext(), ic_chevron_right_black_24dp));
            ivTeamChevron.setImageDrawable(ContextCompat.getDrawable(getContext(), ic_chevron_right_black_24dp));
            tabOpen = 2;
        } else {
            LayoutTransition lt = collapseTransition();
            LayoutTransition lt2 = collapseTransition();
            LayoutTransition lt3 = collapseTransition();

            cvContacts.setLayoutTransition(lt);
            cvTeam.setLayoutTransition(lt2);
            cvAccount.setLayoutTransition(lt3);

            svLodging.setVisibility(View.GONE);
            ivLodgingChevron.setImageDrawable(ContextCompat.getDrawable(getContext(), ic_chevron_right_black_24dp));
            tabOpen = 0;
        }
    }

    private void openContacts() {
        if (tabOpen != 3) {
            svContacts.setVisibility(View.VISIBLE);
            ivContactsChevron.setImageDrawable(ContextCompat.getDrawable(getContext(), ic_expand_more_black_24dp));
            svAccounts.setVisibility(View.GONE);
            svLodging.setVisibility(View.GONE);
            llTeamlist.setVisibility(View.GONE);
            ivLodgingChevron.setImageDrawable(ContextCompat.getDrawable(getContext(), ic_chevron_right_black_24dp));
            ivAccountChevron.setImageDrawable(ContextCompat.getDrawable(getContext(), ic_chevron_right_black_24dp));
            ivTeamChevron.setImageDrawable(ContextCompat.getDrawable(getContext(), ic_chevron_right_black_24dp));
            tabOpen = 3;
        } else {
            LayoutTransition lt = collapseTransition();
            LayoutTransition lt2 = collapseTransition();
            LayoutTransition lt3 = collapseTransition();

            cvAccount.setLayoutTransition(lt);
            cvTeam.setLayoutTransition(lt2);
            cvLodging.setLayoutTransition(lt3);

            svContacts.setVisibility(View.GONE);
            ivContactsChevron.setImageDrawable(ContextCompat.getDrawable(getContext(), ic_chevron_right_black_24dp));
            tabOpen = 0;
        }
    }

    private void openTeam() {
        if (tabOpen != 4) {
            llTeamlist.setVisibility(View.VISIBLE);
            ivTeamChevron.setImageDrawable(ContextCompat.getDrawable(getContext(), ic_expand_more_black_24dp));
            svAccounts.setVisibility(View.GONE);
            svLodging.setVisibility(View.GONE);
            svContacts.setVisibility(View.GONE);
            ivLodgingChevron.setImageDrawable(ContextCompat.getDrawable(getContext(), ic_chevron_right_black_24dp));
            ivAccountChevron.setImageDrawable(ContextCompat.getDrawable(getContext(), ic_chevron_right_black_24dp));
            ivContactsChevron.setImageDrawable(ContextCompat.getDrawable(getContext(), ic_chevron_right_black_24dp));

            tabOpen = 4;
        } else {
            LayoutTransition lt = collapseTransition();
            LayoutTransition lt2 = collapseTransition();
            LayoutTransition lt3 = collapseTransition();

            cvContacts.setLayoutTransition(lt);
            cvAccount.setLayoutTransition(lt2);
            cvLodging.setLayoutTransition(lt3);
            llTeamlist.setVisibility(View.GONE);
            ivTeamChevron.setImageDrawable(ContextCompat.getDrawable(getContext(), ic_chevron_right_black_24dp));
            tabOpen = 0;
        }
    }


    private LayoutTransition collapseTransition()
    {
        LayoutTransition lt = new LayoutTransition();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            lt.enableTransitionType(LayoutTransition.CHANGING);

        }


        lt.addTransitionListener(new LayoutTransition.TransitionListener() {
            @Override
            public void startTransition(LayoutTransition layoutTransition, ViewGroup viewGroup, View view, int i) {

            }

            @Override
            public void endTransition(LayoutTransition layoutTransition, ViewGroup viewGroup, View view, int i) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    layoutTransition.disableTransitionType(LayoutTransition.CHANGING);
                }
                viewGroup.setLayoutTransition(layoutTransition);
            }
        });

        return lt;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView: ");
        unbinder.unbind();
    }
}
