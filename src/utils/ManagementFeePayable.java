package utils;

// This interface defines a contract for classes that are eligible to have a management fee calculated.
// Any class that implements this interface must provide an implementation for the calculateManagementFee() method.
public interface ManagementFeePayable
{

    // This method is responsible for calculating the management fee for an account or object implementing this interface.
    double calculateManagementFee();
}
