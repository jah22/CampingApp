//Copyright @jordansfowler

import java.util.ArrayList;

public class CampSiteManager{
  private String name;
  private String address;
  // portia said 7 dollars is fine 
  private double pricePerCamperPerDay;
  private ArrayList<FAQ> frequentlyAskedQuestions;

  private static CampSiteManager campSiteManager;
  private String authcode;

  // managers
  PersonManager personManager;
  ReviewManager reviewManager;
  CabinManager cabinManager;

  private CampSiteManager(String name, String address, double pricePerCamperPerDay, String authCode) {
    /*
     * TODO:
     * init the managers
     */
    this.personManager = new PersonManager(FileIO.getAdmins(),FileIO.getGuardians(),FileIO.getDependents());
    this.reviewManager = new ReviewManager(FileIO.getReviews());
    this.cabinManager = new CabinManager(FileIO.getCabins());

    this.name = name;
    this.address = address;
    this.pricePerCamperPerDay = pricePerCamperPerDay;
    this.frequentlyAskedQuestions = FileIO.getFaqs();
    this.authcode = authCode;
  }
  public static CampSiteManager getInstance(String name, String address, double pricePerCamperPerDay,String authCode) {
    //To-Do
    if(campSiteManager == null){
      return new CampSiteManager(name,address,pricePerCamperPerDay,authCode);
    }
    return campSiteManager;
  }
  public String getName() {
    //To-Do
    return name;
  }
  public String getAddress() {
    //To-Do
    return address;
  }
  public void seeCoordinators() {
    this.personManager.seeCoordinators();
  }
  public String toString() {
      String out = "Cabin: " + this.name;

      // to do:
      // be more fancy 

      return out;
  }
  public void seeAdmins() {
    this.personManager.seeAdmins();; 
  }
  public void seeCabins() {
    this.cabinManager.seeCabins();
  }
  public void seeAllActivities() {
    /*
     * TO DO:
     * Loop thru all activities
     */
  }
  public void seeAllCabinActivities(String cabinId) {
    if(!this.cabinManager.seeCabinActivities(cabinId)){
      System.out.println("A cabin with that ID cannot be found.");
    }
  }
  public void seeCabinCoordinators(String cabinID) {
    if(!this.cabinManager.seeCabinCoordinators(cabinID)){
      System.out.println("A cabin with that ID cannot be found.");
    }
  }
  public boolean addCamperToCabin(Dependent camper) {
    /*
     * TODO this function
     */
    return false;
  }
  public boolean removeCamperFromCabin(Dependent camper) {
    //To-Do
    return false;
  }
  public boolean addCoordinatorToCabin(Dependent coordinator) {
    //To-Do
    return false;
  }
  public boolean removeCoordinatorFromCabin(Dependent coordinator) {
    //To-Do
    return false;
  }
  public void seeAvgReviews() {
    //To-Do
  }
  public boolean seeReviewsByGuardian(String firstName, String lastName) {
    //To-Do
    return false;
  }
  public boolean seeReviewsByRating(int rating) {
    //To-Do
    return false;
  }
  public boolean addReview(String firstName, String lastName, int rating, String text) {
    //To-Do
    return false;
  }
  public boolean registerGuardian() {
    //To-Do
    return false;
  }
  public boolean registerDependent() {
    //To-Do
    return false;
  }
  public boolean removeDependent(Dependent dept) {
    //To-Do
    return false;
  }
  public boolean removeGuardian(String firstName, String lastName) {
    //To-Do
    return false;
  }
  public boolean registerAdmin(CampAdmin admin) {
    //To-Do
    return false;
  }
  public boolean registerCoordinator(Dependent coord) {
    //To-Do
    return false;
  }
  public boolean removeCoordinator(Dependent coord) {
    //To-Do
    return false;
  }
  public boolean removeCamper(Dependent camper) {
    //To-Do
    return false;
  }
  public boolean payForCamper(String camperFirstName, String camperLastName) {
    //To-Do
    return false;
  }
  public boolean refundGuardian(String camperFirstName, String capmerLastName) {
    //To-Do
    return false;
  }
  public boolean signUpDependentForCabin(String guardianUserName, String guardianPassword) {
    //To-Do
    return false;
  }
  public boolean viewDependents(String username, String password) {
    //To-Do
    return false;
  }
  public boolean addDependent(String guardianUsername, String guardianPassword) {
    //To-Do
    return false;
  }
  public boolean getDependentCabin(Dependent dept) {
    //To-Do
    return false;
  }
  public boolean showDependentContacts(Dependent dept) {
    //To-Do
    return false;
  }
  public boolean hasPaid(String firstName, String lastName) {
    return personManager.getHasBeenPaidFor(firstName,lastName);
  }
  public boolean writeReview(Guardian author, String text, int rating) {
    //To-Do
    return false;
  }
  public boolean removeReview(Guardian author, String text, int rating) {
    //To-Do
    return false;
  }
  public boolean updateLoginInformation(String curUsername, String curPassword) {
    //To-Do
    return false;
  }
  public boolean getMedicalNotes(String username, String password) {
    //To-Do
    return false;
  }
  public boolean getContactInformation(String authUsername, String authPassword) {
    //To-Do
    return false;
  }
  public boolean login() {
    //To-Do
    return false;
  }
  public boolean logout() {
    //To-Do
    return false;
  }
}