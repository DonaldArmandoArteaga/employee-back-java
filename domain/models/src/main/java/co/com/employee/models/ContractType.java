package co.com.employee.models;

public enum ContractType {

    HOURLY_SALARY("HourlySalaryEmployee"),
    MONTHLY_SALARY("MonthlySalaryEmployee");

    private String contractType;

    ContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getContractType() {
        return contractType;
    }
}
