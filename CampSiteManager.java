/**
 * Manager for Camp Site
 * @author Jacob Hammond, Jordan fowler, Lex Whalen, Tze-Chen Lin
 * CSCE 247
 * October 28, 2022
 */
import java.util.ArrayList;
import java.util.UUID;

public class CampSiteManager{
  private ThemeManager themeManager;
  private int year;
  private String startMonth;
  private UUID themeId;

  private String name;
  private String address;

  private ArrayList<FAQ> frequentlyAskedQuestions;

  private static CampSiteManager campSiteManager;

  // managers
  PersonManager personManager;
  ReviewManager reviewManager;
  CabinManager cabinManager;
  /**
   * Constructor for CampsiteManager
   * @param name is person's name
   * @param address is person's address
   * @param year is person's year
   * @param startMonth 
   * @param themeManager
   */
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
  /**
   * Creates instance of CampSiteManager
   * @return CampSiteManager
   */
  public static CampSiteManager getInstance() {
    if(campSiteManager == null){
      campSiteManager = FileIO.getCamp();
      return campSiteManager;
    }
    return campSiteManager;
  }
  /**
   * 
   * @param name
   * @param address
   * @param year
   * @param startMonth
   * @param themeManager
   * @return
   */
  public static CampSiteManager getInstance(String name, String address, int year,String startMonth, ThemeManager themeManager) {
    //To-Do
    if(campSiteManager == null){
      campSiteManager = new CampSiteManager(name, address, year,startMonth,themeManager);
      return campSiteManager;
    }
    return campSiteManager;
  }
  /**
   * Gives name
   * @return name
   */
  public String getName() {
    return name;
  }
  /**
   * Set's name
   * @param name is the name
   */
  public void setName(String name){
    this.name = name;
  }
  /**
   * Get's address
   * @return address
   */
  public String getAddress() {
    return address;
  }
  /**
   * Get's price per camper
   * @return price "7"
   */
  public int getPricePerCamper() {
    return 7;
  }
  /**
   * Get's current theme ID
   * @return theme ID
   */
  public UUID getCurrentThemeID() {
    return this.themeManager.getId();
  }
  /**
   * Allows viewing of Coordinators
   */
  public void viewCoordinators() {
    this.personManager.viewCoordinators();
  }
  /**
   * Prints information about campsite
   */
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
  /**
   * Allows viewing of Admins
   */
  public void viewAdmins() {
    this.personManager.viewAdmins();; 
  }
  /**
   * Allows viewing of cabins
   */
  public void viewCabins() {
    this.cabinManager.viewCabins();
  }
  /**
   * Allows viewing cabins by coordinator
   * @param coordinator
   */
  public void viewCabinsByCoordinator(Dependent coordinator){
    this.cabinManager.viewCabinsByCoordinator(coordinator);
  }
  /**
   * Allows viewing of cabin schedules based on coordinator
   * @param coordinator is the coordinator of the cabins
   */
  public void viewCabinSchedulesByCoordinator(Dependent coordinator){
    this.cabinManager.viewCabinSchedulesByCoordinator(coordinator);
  }
  /**
   * 
   * @param cabinID
   */
  public void viewCabinCoordinators(String cabinID) {
    if(!this.cabinManager.viewCabinCoordinators(cabinID)){
      System.out.println("A cabin with that ID cannot be found.");
    }
  }
  /**
   * Checks cabins for dependents
   * @param g is the guardian 
   * @return true or false
   */
  public boolean checkCabinsForDependents(Guardian g){
    return this.cabinManager.checkCabinsForDependents(g);
  }
  /**
   * Gets cabin by index
   * @param index is the index
   * @return the Cabin
   */
  public Cabin getCabinByIndex(int index){
    return this.cabinManager.getCabinByIndex(index);
  }
  /**
   * Adds camper to cabin
   * @param camper is camper to be added
   * @param cabin is the cabin where camper is added
   * @return true/false
   */
  public boolean addCamperToCabin(Dependent camper,Cabin cabin) {
    return this.cabinManager.addCamperToCabin(camper,cabin);
  }
  /**
   * View all reviews
   */
  public void viewAllReviews(){
    this.reviewManager.viewAllReviews();
  }
  /**
   * view reviews by author
   * @param author is author of review
   */
  public void viewReviewsByAuthor(String author) {
    this.reviewManager.viewReviewsByAuthor(author);
  }
  /**
   * view reviews by rating
   * @param rating is the rating
   */
  public void viewReviewsByRating(int rating) {
    this.reviewManager.viewReviewsByRating(rating);
  }
  /**
   * Add reviews
   * @param authorFullName are author
   * @param rating is the rating
   * @param title is the title
   * @param body is the body
   */
  public void addReview(String authorFullName, int rating,String title, String body){
    this.reviewManager.addReview(authorFullName, rating,title,body);
  }
  /**
   * Allows for guardian registration
   * @param firstName is first name
   * @param lastName is last name
   * @param birthDate birth day
   * @param username is username
   * @param password is password
   * @param email is email
   * @param phone is phone
   * @param address is address
   * @return Guardian
   */
  public Guardian registerGuardian(String firstName, String lastName, String birthDate, String username, String password, String email, String phone,String address) {
    return this.personManager.registerGuardian(firstName, lastName, birthDate, username, password, email, phone,address) ;
  }
  /**
   * Remove Dependents
   * @param g is the guardian
   * @param dept is the dependent
   * @return true/false
   */
  public boolean removeDependent(Guardian g, Dependent dept) {
    return this.personManager.removeDependent(g,dept);
  }
  /**
   * Allows for admin registration
   * @param admin is administrator
   * @return CampAdmin
   */
  public CampAdmin registerAdmin(CampAdmin admin) {
    return this.personManager.registerAdmin(admin);
  }
  /**
   * Allows for Coordinator registration
   * @param firstName is the first name
   * @param lastName is the last name
   * @param username is the user name
   * @param password is the password
   * @param birthDate is the birth day
   * @param phone is the phone number
   * @param email is the email
   * @return is a dependent
   */
  public Dependent registerCoordinator(String firstName, String lastName, String username, String password, String birthDate, String phone, String email) {
    return this.personManager.registerCoordinator(firstName, lastName, username, password, birthDate, phone, email);
  }
  /**
   * Allows for viewing of dependents from guardian ID
   * @param guardianId is the guardian ID
   * @return true/false
   */
  public boolean viewDependentsFromGuardian(UUID guardianId){
    Guardian g = this.personManager.getGuardianById(guardianId);
    if(g!=null){
      g.viewDependents();
      return true;
    }
    return false;
  }
  /**
   * View Cabin Couns
   * @param cabinIndex is cabin index
   * @return true/false
   */
  public boolean viewCabinCouncelors(int cabinIndex) {
    Cabin cabin = this.cabinManager.getCabinByIndex(cabinIndex);
    if(cabin != null){
      System.out.println("Cabin: " + cabin.getCabinName() + "'s councelors\n");
      cabin.viewCabinCouncelors();
      return true;
    }
    return false;
  }
  /**
   * Allows for dependent to get added
   * @param guardianId is ID
   * @param firstName is first name
   * @param lastName is last name
   * @param birthDate is birth day
   * @param address is address
   * @param medNotes are med notes
   * @param ems is ems info
   */
  public void addDependent(UUID guardianId,String firstName, String lastName, String birthDate, String address,ArrayList<String> medNotes, ArrayList<EmergencyContact> ems) {
    this.personManager.addDependent(guardianId, firstName, lastName, birthDate, address,medNotes,ems);
  }
  /**
   * Allows for written reviews
   * @param firstName is the author first name
   * @param lastName is the last name
   * @param rating is the rating
   * @param title is the title of review
   * @param text is the review text
   */
  public void writeReview(String firstName,String lastName,int rating, String title, String text) {
    this.reviewManager.addReview(firstName + " " + lastName,rating,title,text);
  }
  /**
   * Removes Reviews
   * @param author is the author
   * @param title is the title
   */
  public void removeReview(Guardian author, String title) {
    if(!this.reviewManager.removeReview(title, author)){
      System.out.println("Cannot remove review. Check the title or the permissions.\n");
      return;
    }
    System.out.println("Review successfully removed.\n");
  }
  /**
   * View Dependents
   * @param id is the UUID
   * @return true/false
   */
  public boolean viewDependent(String id) {
    UUID uuid = UUID.fromString(id);
    Dependent dep = this.personManager.getDependentById(uuid);
    if(dep!=null){
      System.out.println(dep);
      return true;
    }
    return false;
  }
  /**
   * View Contact Info
   * @param id is UUID
   * @return true/false
   */
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
  /**
   * Allows guardina to login
   * @param username is usernmae
   * @param password is password
   * @return Guardian
   */
  public Guardian loginGuardian(String username, String password){
    return this.personManager.loginGuardian(username, password);
  }
  /**
   * Allows dependent to login
   * @param username is usernmae
   * @param password is password
   * @return dependent
   */
  public Dependent loginDependent(String username, String password){
    return this.personManager.loginDependent(username,password);
  }
  /**
   * Allow admin to login
   * @param username is username
   * @param password is password
   * @return CampAdmin
   */
  public CampAdmin loginAdmin(String username, String password){
    return this.personManager.loginAdmin(username,password);
  }
  /**
   * Allows logging out
   * @param username is username
   * @param password is password
   * @return true/false
   */
  public boolean logout(String username, String password) {
    return this.personManager.logout(username,password);
  }
  /**
   * View Camp
   */
  public void viewCamp(){
    System.out.println(this.toString());
  }
  /**
   * Checks if guardian has dependents
   * @param g is guardian
   * @return true/false
   */
  public boolean guardianHasDependents(Guardian g){
    return this.personManager.guardianHasDependents(g);
  }
  /**
   * checks if guardian has campers registered 
   * @param g is guardian
   * @return true/false
   */
  public boolean guardianHasCampersRegistered(Guardian g){
    return this.cabinManager.guardianHasCampersRegistered(g);
  }
  /**
   * chekcs if guardian has registered cabin
   * @param g is Guardian
   */
  public void viewGuardianRegisteredCabins(Guardian g){
    // view the cabins that you have registered
    this.cabinManager.viewGuardianRegisteredCabins(g);
  }
  /**
   * View Cabin names
   */
  public void viewCabinNames(){
    this.cabinManager.viewCabinNames();
  }
  /**
   * View Cabin by Index
   * @param index is the cabin index
   */
  public void viewCabinByIndex(int index){
    this.cabinManager.viewCabinByIndex(index);
  }
  /**
   * View camper names by guardian
   * @param guardianId  is gurdian UUID
   */
  public void viewCamperNamesByGuardian(UUID guardianId){
    this.personManager.viewCamperNamesByGuardian(guardianId);
  }
  /**
   * collects dependent by name 
   * @param guardianId is UUID
   * @param firstName is first name
   * @param lastName is last name
   * @return dependent being searched for
   */
  public Dependent getDependentByName(UUID guardianId,String firstName, String lastName){
    return this.personManager.getDependentByName(guardianId,firstName, lastName);
  }
  /**
   * get cabin count
   * @return number of cabins
   */
  public int getCabinCount(){
    return this.cabinManager.getCabinCount();
  }
  /**
   * View emergency contacts
   * @param dep is the dependent
   */
  public void viewEmergencyContacts(Dependent dep){
    this.personManager.viewEmergencyContacts(dep) ;
  }
  /**
   * Get the year
   * @return year
   */
  public int getYear(){
    return this.year;
  }
  /**
   * Sets year
   * @param year is year
   */
  public void setYear(int year){
    this.year = year; 
  }
  /**
   * Get avg rating
   * @return the rating
   */
  public double getAvgRating(){
    return this.reviewManager.getAvgRating();
  }
  /**
   * Get Camp rosters
   * @param coordinator is coordinator we want roster from
   * @return roster
   */
  public String getCampRosters(Dependent coordinator){
    return this.cabinManager.getCampRosters(coordinator);
  }
  /**
   * Get dependent over cabins
   * @param user is dependent
   * @return list of cabins containing dependent
   */
  public ArrayList<Cabin> getDependentCabins(Dependent user){
    return this.cabinManager.getDependentCabins(user);
  }
  /**
   * Get count based on dependent
   * @param user is the dependent
   * @return number of cabins
   */
  public int getCabinCountByDependent(Dependent user){
    return this.cabinManager.getCabinCountByDependent(user);
  }
  /**
   * Gets cabin roster
   * @param c is the cabim
   * @return the roster for that cabin
   */
  public String getCabinRoster(Cabin c){
    return this.cabinManager.getCabinRoster(c);
  }
  /**
   * Sets the theme
   * @param t is the theme
   */
  public void setThemeManager(ThemeManager t){
    this.themeId= t.getId();
    this.themeManager = t;
  }
  /**
   * Resets the camp
   */
  public void resetCamp(){
    this.frequentlyAskedQuestions = new ArrayList<FAQ>();
    this.name = "";
    this.year = -1;
    this.address = "";
    this.personManager.reset();
    this.reviewManager = new ReviewManager();
    this.cabinManager = new CabinManager();
  }
  /**
   * Setter for cabin manger
   * @param cabinManager is the cabin manager for this camp
   */
  public void setCabinManager(CabinManager cabinManager){
    this.cabinManager = cabinManager; 
  }
  /**
   * Setter for address
   * @param address is the address
   */
  public void setAddress(String address){
    this.address = address;
  }
  /**
   * Setter for the starting month
   * @param month is the start month
   */
  public void setStartMonth(String month){
    this.startMonth = month;
  }
  /**
   * Gets the starting month
   * @return the starting month
   */
  public String getStartMonth(){
    return this.startMonth;
  }
  /**
   * Getter for Session count
   * @return the number os sessions
   */
  public int getSessionCount(){
    return this.themeManager.getThemeCount();
  }
  /**
   * Allows for viewing of the themes
   */
  public void viewThemes(){
    this.themeManager.viewThemes();
  }
  /**
   * Allows for viewing of the cabin sessions
   * @param cabinIndex is the index of the cabin that you want to view
   * @param sessionIndex is the session you want to look at it
   */
  public void viewIndexCabinSession(int cabinIndex,int sessionIndex){
    this.cabinManager.viewIndexCabinSession(cabinIndex, sessionIndex);
  }
  /**
   * Saves people, themes, reviews, cabins, and schedules and writes to file
   */
  public void save(){
    // saves all of the current data

    // 1. Save People
    this.personManager.save();

    // 2. Save Themes
    this.themeManager.save();
    // 3. Save Reviews
    this.reviewManager.save();
    // 4. Save Cabins & Schedules
    this.cabinManager.save();

    //5. save camp
    FileIO.writeCamp(this.campSiteManager);
  }
}
