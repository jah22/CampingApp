//Copyright @jordansfowler

import java.util.ArrayList;

public class CampSiteManager{
  private String name;
  private String address;
  private float pricePerCamperPerDay;
  private ArrayList<String> frequentlyAskedQuestions;

  private static CampSiteManager campSiteManager;
  private String authcode;

  // managers
  PersonManager personManager;
  ReviewManager reviewManager;
  CabinManager cabinManager;

  private CampSiteManager() {
    /*
     * TODO:
     * init the managers
     */
  }
  private static CampSiteManager getInstance() {
    //To-Do
    if(campSiteManager == null){
      return new CampSiteManager();
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
    //To-Do

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
  public boolean seeReviewsByGaurdian(String firstName, String lastName) {
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
  public boolean registerGaurdian() {
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
  public boolean removeGaurdian(String firstName, String lastName) {
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
  public boolean refundGaurdian(String camperFirstName, String capmerLastName) {
    //To-Do
    return false;
  }
  public boolean signUpDependentForCabin(String gaurdianUserName, String gaurdianPassword) {
    //To-Do
    return false;
  }
  public boolean viewDependents(String username, String password) {
    //To-Do
    return false;
  }
  public boolean addDependent(String gaurdianUsername, String guardianPassword) {
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
  public boolean hasPaid(Dependent camper) {
    //To-Do
    return false;
  }
  public boolean writeReview(Gaurdian author, String text, int rating) {
    //To-Do
    return false;
  }
  public boolean removeReview(Gaurdian author, String text, int rating) {
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