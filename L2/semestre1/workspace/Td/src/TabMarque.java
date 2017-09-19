public class TabMarque{
  private int scoreLoc;
  private int scoreVis;


  public TabMarque(){
    this.scoreLoc = 0;
    this.scoreVis = 0;
  }

  public int getScoreLoc(){
    return this.scoreLoc;
  }

  public int getScoreVis(){
    return this.scoreVis;
  }

  public void setAjouterLoc(int pts){
    this.scoreLoc += pts;
  }

  public void setAjouterVis(int pts){
    this.scoreVis += pts;
  }

  public void setAjouterPts(int pts,boolean loc){
    if(loc){
      this.scoreLoc += pts;
    }
    else{
      this.scoreVis += pts;
    }
  }

  public void afficheScore(){
    System.out.println("Locaux        " + "Visiteurs");
    System.out.println("\n   " + this.scoreLoc + "      :       " + this.scoreVis);
  }


}
