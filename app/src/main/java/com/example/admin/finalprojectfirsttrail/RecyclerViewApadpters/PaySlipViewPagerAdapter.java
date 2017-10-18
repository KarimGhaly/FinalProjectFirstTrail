package com.example.admin.finalprojectfirsttrail.RecyclerViewApadpters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.finalprojectfirsttrail.InfoClass.PaySlipInfoClass;
import com.example.admin.finalprojectfirsttrail.R;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.admin.finalprojectfirsttrail.MainActivity.TAG;

/**
 * Created by Admin on 10/17/2017.
 */

public class PaySlipViewPagerAdapter extends PagerAdapter {
    Context context;
    List<PaySlipInfoClass> payslips;
    @BindView(R.id.tvDate_payDate)
    TextView tvDatePayDate;
    @BindView(R.id.tvDate_startDate)
    TextView tvDateStartDate;
    @BindView(R.id.tvDate_endDate)
    TextView tvDateEndDate;
    @BindView(R.id.tvEarning_regularRate)
    TextView tvEarningRegularRate;
    @BindView(R.id.tvEarning_regularHours)
    TextView tvEarningRegularHours;
    @BindView(R.id.tvEarning_regularPay)
    TextView tvEarningRegularPay;
    @BindView(R.id.tvEarning_overtimeRate)
    TextView tvEarningOvertimeRate;
    @BindView(R.id.tvEarning_overtimeHours)
    TextView tvEarningOvertimeHours;
    @BindView(R.id.tvEarning_overTimePay)
    TextView tvEarningOverTimePay;
    @BindView(R.id.tvEarning_holidayHours)
    TextView tvEarningHolidayHours;
    @BindView(R.id.tvEarning_holidayPay)
    TextView tvEarningHolidayPay;
    @BindView(R.id.tvEarning_ptoHours)
    TextView tvEarningPtoHours;
    @BindView(R.id.tvEarning_ptoPay)
    TextView tvEarningPtoPay;
    @BindView(R.id.tvEarning_totalHours)
    TextView tvEarningTotalHours;
    @BindView(R.id.tvEarning_totalPay)
    TextView tvEarningTotalPay;
    @BindView(R.id.tvExpAdv_expenses)
    TextView tvExpAdvExpenses;
    @BindView(R.id.tvExpAdv_advances)
    TextView tvExpAdvAdvances;
    @BindView(R.id.tvDeduction_federalTax)
    TextView tvDeductionFederalTax;
    @BindView(R.id.tvDeduction_federalTaxYTD)
    TextView tvDeductionFederalTaxYTD;
    @BindView(R.id.tvDeduction_stateTax)
    TextView tvDeductionStateTax;
    @BindView(R.id.tvDeduction_stateTaxYTD)
    TextView tvDeductionStateTaxYTD;
    @BindView(R.id.tvDeduction_socialSecurityTax)
    TextView tvDeductionSocialSecurityTax;
    @BindView(R.id.tvDeduction_socialSecurityTaxYTD)
    TextView tvDeductionSocialSecurityTaxYTD;
    @BindView(R.id.tvDeduction_medicareTax)
    TextView tvDeductionMedicareTax;
    @BindView(R.id.tvDeduction_medicareTaxYTD)
    TextView tvDeductionMedicareTaxYTD;
    @BindView(R.id.tvDeduction_medicareInsurance)
    TextView tvDeductionMedicareInsurance;
    @BindView(R.id.tvDeduction_medicareInsuranceYTD)
    TextView tvDeductionMedicareInsuranceYTD;
    @BindView(R.id.tvDeduction_visionInsurance)
    TextView tvDeductionVisionInsurance;
    @BindView(R.id.tvDeduction_visionInsuranceYTD)
    TextView tvDeductionVisionInsuranceYTD;
    @BindView(R.id.tvDeduction_dentalInsurance)
    TextView tvDeductionDentalInsurance;
    @BindView(R.id.tvDeduction_dentalInsuranceYTD)
    TextView tvDeductionDentalInsuranceYTD;
    @BindView(R.id.tvDeduction_stdisabilityInsurance)
    TextView tvDeductionStdisabilityInsurance;
    @BindView(R.id.tvDeduction_stdisabilityInsuranceYTD)
    TextView tvDeductionStdisabilityInsuranceYTD;
    @BindView(R.id.tvDeduction_ltdisabilityInsurance)
    TextView tvDeductionLtdisabilityInsurance;
    @BindView(R.id.tvDeduction_ltdisabilityInsuranceYTD)
    TextView tvDeductionLtdisabilityInsuranceYTD;
    @BindView(R.id.tvDeduction_lifeInsurance)
    TextView tvDeductionLifeInsurance;
    @BindView(R.id.tvDeduction_lifeInsuranceYTD)
    TextView tvDeductionLifeInsuranceYTD;
    @BindView(R.id.tvDeduction_advance)
    TextView tvDeductionAdvance;
    @BindView(R.id.tvDeduction_advanceYTD)
    TextView tvDeductionAdvanceYTD;
    @BindView(R.id.tvDeduction_totalPay)
    TextView tvDeductionTotalPay;
    @BindView(R.id.tvDeduction_totalPayYTD)
    TextView tvDeductionTotalPayYTD;
    @BindView(R.id.tvTotals_netPay)
    TextView tvTotalsNetPay;
    @BindView(R.id.tvTotals_grossPayYTD)
    TextView tvTotalsGrossPayYTD;
    @BindView(R.id.tvTotals_netPayYTD)
    TextView tvTotalsNetPayYTD;

    public PaySlipViewPagerAdapter(Context context, List<PaySlipInfoClass> payslips) {
        this.context = context;
        this.payslips = payslips;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.pager_list_item, container, false);
        ButterKnife.bind(this,view);
        ButterKnife.setDebug(true);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        PaySlipInfoClass payslib = payslips.get(position);
        Log.d(TAG, "instantiateItem: "+payslib.getPayDate().toString());
        tvDatePayDate.setText(dateFormat.format(payslib.getPayDate()));
        tvDateStartDate.setText(dateFormat.format(payslib.getFrom()));
        tvDateEndDate.setText(dateFormat.format(payslib.getTo()));
        tvEarningRegularRate.setText(String.valueOf(payslib.getHourlyRate()));
        tvEarningRegularHours.setText(String.valueOf(payslib.getTotalHours()));
        tvEarningRegularPay.setText(String.valueOf(payslib.getHourlyRate() * payslib.getTotalHours()));
        tvEarningOvertimeRate.setText(String.valueOf(payslib.getOvertimeRate()));
        tvEarningOvertimeHours.setText(String.valueOf(payslib.getOvertimeHours()));
        tvEarningOverTimePay.setText(String.valueOf(payslib.getOvertimeHours() * payslib.getOvertimeRate()));
        tvEarningHolidayHours.setText(String.valueOf(payslib.getHolidayHours()));
        tvEarningHolidayPay.setText(String.valueOf(payslib.getHolidayHours() * payslib.getHourlyRate()));
        tvEarningPtoHours.setText(String.valueOf(payslib.getPtoUsedHours()));
        tvEarningPtoPay.setText(String.valueOf(payslib.getPtoUsedHours() * payslib.getHourlyRate()));
        tvEarningTotalHours.setText(String.valueOf(payslib.getTotalGrossHours()));
        tvEarningTotalPay.setText(String.valueOf(payslib.getTotalPay()));

        tvExpAdvExpenses.setText(String.valueOf(payslib.getExpensesPay()));
        tvExpAdvAdvances.setText(String.valueOf(payslib.getAdvancesPay()));

        tvDeductionFederalTax.setText(String.valueOf(payslib.getFederalTax()));
        tvDeductionFederalTaxYTD.setText(String.valueOf(payslib.getFederalTaxYTD()));
        tvDeductionSocialSecurityTax.setText(String.valueOf(payslib.getSocialSecurityTax()));
        tvDeductionSocialSecurityTaxYTD.setText(String.valueOf(payslib.getSocialSecurityTaxYTD()));
        tvDeductionStateTax.setText(String.valueOf(payslib.getStateTax()));
        tvDeductionStateTaxYTD.setText(String.valueOf(payslib.getStateTaxYTD()));
        tvDeductionMedicareTax.setText(String.valueOf(payslib.getMedicareTax()));
        tvDeductionMedicareTaxYTD.setText(String.valueOf(payslib.getMedicareTaxYTD()));
        tvDeductionMedicareInsurance.setText(String.valueOf(payslib.getMedicalInsurance()));
        tvDeductionMedicareInsuranceYTD.setText(String.valueOf(payslib.getMedicalInsuranceYTD()));
        tvDeductionVisionInsurance.setText(String.valueOf(payslib.getVisionInsurance()));
        tvDeductionVisionInsuranceYTD.setText(String.valueOf(payslib.getVisionInsuranceYTD()));
        tvDeductionDentalInsurance.setText(String.valueOf(payslib.getDentalInsurance()));
        tvDeductionDentalInsuranceYTD.setText(String.valueOf(payslib.getDentalInsuranceYTD()));
        tvDeductionStdisabilityInsurance.setText(String.valueOf(payslib.getShortTermDisabilityInsurance()));
        tvDeductionStdisabilityInsuranceYTD.setText(String.valueOf(payslib.getShortTermDisabilityInsuranceYTD()));
        tvDeductionLtdisabilityInsurance.setText(String.valueOf(payslib.getLongTermDisabilityInsurance()));
        tvDeductionLtdisabilityInsuranceYTD.setText(String.valueOf(payslib.getLongTermDisabilityInsuranceYTD()));
        tvDeductionLifeInsurance.setText(String.valueOf(payslib.getLifeInsurance()));
        tvDeductionLifeInsuranceYTD.setText(String.valueOf(payslib.getLifeInsuranceYTD()));
        tvDeductionAdvance.setText(String.valueOf(payslib.getAdvancesDeduction()));
        tvDeductionAdvanceYTD.setText(String.valueOf(payslib.getAdvancesDeductionYTD()));
        tvDeductionTotalPay.setText(String.valueOf(payslib.getTotalDeductions()));
        tvDeductionTotalPayYTD.setText(String.valueOf(payslib.getTotalYTDDeductions()));


        tvTotalsNetPay.setText(String.valueOf(payslib.getTotalNetPay()));
        tvTotalsGrossPayYTD.setText(String.valueOf(payslib.getGrosspayYTD()));
        tvTotalsNetPayYTD.setText(String.valueOf(payslib.getNetpayYTD()));

        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return payslips.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (LinearLayout) object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
