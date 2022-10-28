
import java.util.ArrayList;
import java.util.UUID;

public class CampSiteManager{
  // to do: add themes
  private ThemeManager themeManager;
  private int year;
  private String startMonth;

  private String name;
  private String address;

  private ArrayList<FAQ> frequentlyAskedQuestions;

  private static CampSiteManager campSiteManager;

  // managers
  PersonManager personManager;
  ReviewManager reviewManager;
  CabinManager cabinManager;

  private CampSiteManager(String name, String address, int year,String startMonth,ThemeManager themeManager) {
    this.personManager = new PersonManager(FileIO.getAdmins(),FileIO.getGuardians(),FileIO.getDependents(),FileIO.getEmergencyContacts());
    this.reviewManager = new ReviewManager(FileIO.getReviews());
    this.cabinManager = new CabinManager(FileIO.getCabins());

    this.name = name;
    this.address = address;
    this.frequentlyAskedQuestions =FileIO.getFaqs();
    this.year = year;
    this.startMonth = startMonth;
    this.themeManager=themeManager;
  }
  public static CampSiteManager getInstance() {
    //To-Do
    if(campSiteManager == null){
      campSiteManager = FileIO.getCamp();
      return campSiteManager;
    }
    return campSiteManager;
  }
  public static CampSiteManager getInstance(String name, String address, int year,String startMonth, ThemeManager themeManager) {
    //To-Do
    if(campSiteManager == null){
      campSiteManager = new CampSiteManager(name, address, year,startMonth,themeManager);
      return campSiteManager;
    }
    return campSiteManager;
  }
  public String getName() {
    return name;
  }
  public void setName(String name){
    this.name = name;
  }
  public String getAddress() {
    return address;
  }
  public void viewCoordinators() {
    this.personManager.viewCoordinators();
  }
  public String toString() {
      String out = "Cabin: " + this.name +"\n";
      out += "Year: " + this.year + "\n";
      out += "Rating: " + this.getAvgRating() +"\n";
      out += "Start month: "+ this.getStartMonth() +"\n";
      out += "Themes: "+"\n";
      out += this.themeManager.toString();

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
  public void viewCabinsByCoordinator(Dependent coordinator){
    this.cabinManager.viewCabinsByCoordinator(coordinator);
  }
  public void viewCabinSchedulesByCoordinator(Dependent coordinator){
    this.cabinManager.viewCabinSchedulesByCoordinator(coordinator);
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
  public void viewAllReviews(){
    this.reviewManager.viewAllReviews();
  }
  public void viewReviewsByAuthor(String author) {
    this.reviewManager.viewReviewsByAuthor(author);
  }
  public void viewReviewsByRating(int rating) {
    this.reviewManager.viewReviewsByRating(rating);
  }
  public void addReview(String authorFullName, int rating,String title, String body){
    this.reviewManager.addReview(authorFullName, rating,title,body);
  }
  public Guardian registerGuardian(String firstName, String lastName, String birthDate, String username, String password, String email, String phone,String address) {
    return this.personManager.registerGuardian(firstName, lastName, birthDate, username, password, email, phone,address) ;
  }
  public boolean registerDependent() {
    //To-Do
    return false;
  }
  public boolean removeDependent(Guardian g, Dependent dept) {
    return this.personManager.removeDependent(g,dept);
  }
  public boolean removeGuardian(String firstName, String lastName) {
    //To-Do
    return false;
  }
  public CampAdmin registerAdmin(CampAdmin admin) {
    //To-Do
    return this.personManager.registerAdmin(admin);
  }
  public Dependent registerCoordinator(String firstName, String lastName, String username, String password, String birthDate, String phone, String email) {
    return this.personManager.registerCoordinator(firstName, lastName, username, password, birthDate, phone, email);
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
  public void viewCabinByIndex(int index){
    this.cabinManager.viewCabinByIndex(index);
  }
  public void viewCamperNamesByGuardian(UUID guardianId){
    this.personManager.viewCamperNamesByGuardian(guardianId);
  }
  public Dependent getDependentByName(UUID guardianId,String firstName, String lastName){
    return this.personManager.getDependentByName(guardianId,firstName, lastName);
  }
  public int getCabinCount(){
    return this.cabinManager.getCabinCount();
  }
  public void viewEmergencyContacts(Dependent dep){
    this.personManager.viewEmergencyContacts(dep) ;
  }
  public int getYear(){
    return this.year;
  }
  public void setYear(int year){
    this.year = year; 
  }
  public double getAvgRating(){
    return this.reviewManager.getAvgRating();
  }
  public String getCampRosters(Dependent coordinator){
    return this.cabinManager.getCampRosters(coordinator);
  }
  public ArrayList<Cabin> getDependentCabins(Dependent user){
    return this.cabinManager.getDependentCabins(user);
  }
  public int getCabinCountByDependent(Dependent user){
    return this.cabinManager.getCabinCountByDependent(user);
  }
  public String getCabinRoster(Cabin c){
    return this.cabinManager.getCabinRoster(c);
  }
  public void setThemeManager(ThemeManager t){
    this.themeManager = t;
  }
  public void resetCamp(){
    this.frequentlyAskedQuestions = new ArrayList<FAQ>();
    this.name = "";
    this.year = -1;
    this.address = "";
    this.personManager = new PersonManager();
    this.reviewManager = new ReviewManager();
    this.cabinManager = new CabinManager();
  }
  public void setCabinManager(CabinManager cabinManager){
    this.cabinManager = cabinManager; 
  }
  public void setAddress(String address){
    this.address = address;
  }
  public void setStartMonth(String month){
    this.startMonth = month;
  }
  public String getStartMonth(){
    return this.startMonth;
  }
  public int getSessionCount(){
    return this.themeManager.getThemeCount();
  }
  public void viewThemes(){
    this.themeManager.viewThemes();
  }
  public void viewIndexCabinSession(int cabinIndex,int sessionIndex){
    this.cabinManager.viewIndexCabinSession(cabinIndex, sessionIndex);
  }
}