
import java.util.ArrayList;
import java.util.UUID;

public class CampSiteManager{
  // to do: add themes
  private String theme;

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
    this.personManager = new PersonManager(FileIO.getAdmins(),FileIO.getGuardians(),FileIO.getDependents(),FileIO.getEmergencyContacts());
    this.reviewManager = new ReviewManager(FileIO.getReviews());
    this.cabinManager = new CabinManager(FileIO.getCabins());

    this.name = name;
    this.address = address;
    this.pricePerCamperPerDay = pricePerCamperPerDay;
    this.frequentlyAskedQuestions =FileIO.getFaqs();
    this.authcode = authCode;
  }
  public static CampSiteManager getInstance() {
    //To-Do
    if(campSiteManager == null){
      campSiteManager = FileIO.getCamp();
      return campSiteManager;
    }
    return campSiteManager;
  }
  public static CampSiteManager getInstance(String name, String address, double price, String authCode) {
    //To-Do
    if(campSiteManager == null){
      campSiteManager = new CampSiteManager(name, address, price, authCode);
      return campSiteManager;
    }
    return campSiteManager;
  }
  public String getName() {
    return name;
  }
  public String getAddress() {
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
  public void seeReviewsByAuthor(String firstName, String lastName) {
    this.reviewManager.seeReviewsByAuthor(firstName + " " + lastName);
  }
  public void seeReviewsByRating(int rating) {
    this.reviewManager.seeReviewsByRating(rating);
  }
  public void addReview(String firstName, String lastName, int rating, String text) {
    this.reviewManager.addReview(firstName, rating, lastName, text);
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
  public boolean viewDependentsFromGuardian(UUID guardianId){
    Guardian g = this.personManager.getGuardianById(guardianId);
    if(g!=null){
      g.viewDependents();
      return true;
    }
    return false;
  }
  public boolean viewCabinCouncelors(String cabinName) {
    Cabin cabin = this.cabinManager.getCabinByName(cabinName);
    if(cabin != null){
      System.out.println("Cabin: " + cabin.getCabinName() + "'s councelors\n");
      cabin.viewCabinCouncelors();
      return true;
    }
    return false;
  }
  public void addDependent(Guardian user,String firstName, String lastName, String birthDate, String address,ArrayList<String> medNotes, ArrayList<EmergencyContact> ems) {
    this.personManager.addDependent(user, firstName, lastName, birthDate, address,medNotes,ems);
  }
  public boolean getDependentCabin(String id) {

    return false;
  }
  public void writeReview(String firstName,String lastName,int rating, String title, String text) {
    this.reviewManager.addReview(firstName + " " + lastName,rating,title,text);
  }
  public void removeReview(Guardian author, String title) {
    if(!this.reviewManager.removeReview(title, author)){
      System.out.println("Cannot remove review. Check the title or the permissions.\n");
      return;
    }
    System.out.println("Review successfully removed.\n");
  }
  public boolean updateLoginInformation(String curUsername, String curPassword) {
    //To-Do
    return false;
  }
  public boolean viewDependent(String id) {
    UUID uuid = UUID.fromString(id);
    Dependent dep = this.personManager.getDependentById(uuid);
    if(dep!=null){
      System.out.println(dep);
      return true;
    }
    return false;
  }
  public boolean viewContactInformation(String id) {
    UUID uuid = UUID.fromString(id);
    Dependent dep = this.personManager.getDependentById(uuid);
    if(dep!=null){
      System.out.println("Contacts for: " + dep.getFullName());
      dep.viewEmergencyContacts();
    }
    //To-Do
    return false;
  }
  public Guardian loginGuardian(String username, String password){
    return this.personManager.loginGuardian(username, password);
  }
  public Dependent loginDependent(String username, String password){
    return this.personManager.loginDependent(username,password);
  }
  public CampAdmin loginAdmin(String username, String password){
    return this.personManager.loginAdmin(username,password);
  }
  public boolean logout(String username, String password) {
    return this.personManager.logout(username,password);
  }
  public void viewCamp(){
    System.out.println(this.toString());
  }
}