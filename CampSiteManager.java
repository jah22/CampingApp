
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
  public void viewCoordinators() {
    this.personManager.viewCoordinators();
  }
  public String toString() {
      String out = "Cabin: " + this.name;

      // to do:
      // be more fancy 

      return out;
  }
  public void viewAdmins() {
    this.personManager.viewAdmins();; 
  }
  public void viewCabins() {
    this.cabinManager.viewCabins();
  }
  public void viewAllActivities() {
    /*
     * TO DO:
     * Loop thru all activities
     */
  }
  public void viewAllCabinActivities(String cabinId) {
    if(!this.cabinManager.viewCabinActivities(cabinId)){
      System.out.println("A cabin with that ID cannot be found.");
    }
  }
  public void viewCabinCoordinators(String cabinID) {
    if(!this.cabinManager.viewCabinCoordinators(cabinID)){
      System.out.println("A cabin with that ID cannot be found.");
    }
  }
  public boolean checkCabinsForDependents(Guardian g){
    return this.cabinManager.checkCabinsForDependents(g);
  }
  public Cabin getCabinByIndex(int index){
    return this.cabinManager.getCabinByIndex(index);
  }
  public boolean addCamperToCabin(Dependent camper,Cabin cabin) {
    return this.cabinManager.addCamperToCabin(camper,cabin);
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
  public void viewAvgReviews() {
    //To-Do
  }
  public void viewReviewsByAuthor(String firstName, String lastName) {
    this.reviewManager.viewReviewsByAuthor(firstName + " " + lastName);
  }
  public void viewReviewsByRating(int rating) {
    this.reviewManager.viewReviewsByRating(rating);
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
  public boolean viewCabinCouncelors(int cabinIndex) {
    Cabin cabin = this.cabinManager.getCabinByIndex(cabinIndex);
    if(cabin != null){
      System.out.println("Cabin: " + cabin.getCabinName() + "'s councelors\n");
      cabin.viewCabinCouncelors();
      return true;
    }
    return false;
  }
  public void addDependent(UUID guardianId,String firstName, String lastName, String birthDate, String address,ArrayList<String> medNotes, ArrayList<EmergencyContact> ems) {
    this.personManager.addDependent(guardianId, firstName, lastName, birthDate, address,medNotes,ems);
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
  public boolean guardianHasDependents(Guardian g){
    return this.personManager.guardianHasDependents(g);
  }
  public boolean guardianHasCampersRegistered(Guardian g){
    return this.cabinManager.guardianHasCampersRegistered(g);
  }
  public void viewGuardianRegisteredCabins(Guardian g){
    // view the cabins that you have registered
    this.cabinManager.viewGuardianRegisteredCabins(g);
  }
  public void viewCabinNames(){
    this.cabinManager.viewCabinNames();
  }
  public void viewCamperNamesByGuardian(UUID guardianId){
    this.personManager.viewCamperNamesByGuardian(guardianId);
  }
  public Dependent getDependentByName(UUID guardianId,String firstName, String lastName){
    return this.personManager.getDependentByName(guardianId,firstName, lastName);
  }
}