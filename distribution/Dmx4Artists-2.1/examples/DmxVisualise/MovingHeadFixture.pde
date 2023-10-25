class MovingHeadFixture extends FixtureDraw
{
    MovingHeadFixture( DMXFixture fixture, int xPos, int yPos, int xSize, int ySize, boolean rgbMode )
    {
        super( fixture, xPos, yPos, xSize, ySize );
        
        this.rgbMode = rgbMode;
        panFineChannel   = /*fixture.getAddress()  +*/ 1;
        panCoarseChannel = /*fixture.getAddress()  +*/ 2;
        tiltFineChannel   = /*fixture.getAddress() +*/ 3;
        tiltCoarseChannel = /*fixture.getAddress() +*/ 4;
        briChannel = /*fixture.getAddress() +*/ 6;
        rChannel   = /*fixture.getAddress() +*/ 8;
        gChannel   = /*fixture.getAddress() +*/ 9;
        bChannel   = /*fixture.getAddress() +*/ 10;
    }
    
    void draw()
    {
        pushStyle();
        
        if( rgbMode ) // use rgb colour
        {
            float bri = (briChannel == 0) ? 0 : fixture.getParam( briChannel -1 ).getValue();
            float r   = (rChannel   == 0) ? 0 : fixture.getParam( rChannel   -1 ).getValue() * ( bri / 255.0 );
            float g   = (gChannel   == 0) ? 0 : fixture.getParam( gChannel   -1 ).getValue() * ( bri / 255.0 );
            float b   = (bChannel   == 0) ? 0 : fixture.getParam( bChannel   -1 ).getValue() * ( bri / 255.0 );
            
            fill( r, g , b);
        }else            // use colour select
        {
          int colSelect = (int)(fixture.getParam( colSelectChannel ).getValue());
          
          setCol( colSelect );
        }
        
        ellipse( xPos, yPos, xSize, ySize );
        
        stroke(255);
        fill(255);
        // draw pan
        float pan = fixture.getParam( panCoarseChannel - 1 ).getValue();
        pushMatrix();
        translate( xPos, yPos + ySize );
        text("pan", 0, -5 );
        rotate( map( pan, 0, 255.0, 0, TWO_PI ) );
        line( 0, 0, xSize/2, 0);
        popMatrix();
        
        // draw tilt
        float tilt = fixture.getParam( tiltCoarseChannel - 1 ).getValue();
        pushMatrix();
        translate( xPos, yPos + ySize * 2.0 );
        text("tilt", 0, -5 );
        rotate( map( tilt, 0, 255.0, 0, TWO_PI ) );
        line( 0, 0, xSize/2, 0);
        popMatrix();
        
        popStyle();
    }
    
    void setCol( int col )
    {
       switch( col )
       {
          case 0:
            fill(255,0,0);
            break;
          case 1:
            fill(0,255,0);
            break;
          case 2:
            fill(0,0,255);
            break;
          case 3:
            fill(255,0,255);
            break;
          case 4:
            fill(255,255,0);
            break;
       }
    }
    private boolean rgbMode = true; // true - RGB false - colour select
    
    private int briChannel;
    private int rChannel;
    private int gChannel;
    private int bChannel;
    private int wChannel;
    
    private int panFineChannel;
    private int panCoarseChannel;
    private int tiltFineChannel;
    private int tiltCoarseChannel;
    
    private int colSelectChannel = 6; // use channel 6 by default
}
