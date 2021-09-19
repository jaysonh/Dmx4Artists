class Visualiser
{
   
  void add( FixtureDraw fix)
  {
     fixtures.add(fix); 
  }
  
  void draw()
  {
     for( FixtureDraw f : fixtures )
     {
        f.draw(); 
     }
  }
  ArrayList <FixtureDraw> fixtures = new <FixtureDraw>ArrayList();
}
