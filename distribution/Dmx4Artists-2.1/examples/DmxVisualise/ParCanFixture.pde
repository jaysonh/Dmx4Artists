class ParCanFixture extends FixtureDraw
{
    ParCanFixture( DMXFixture fixture, int xPos, int yPos, int xSize, int ySize )
    {
        super( fixture, xPos, yPos, xSize, ySize );
        
        // set up default addresses for a par can bri = 1, r = 2, g = 3, b = 4
        briChannel = fixture.getAddress();
        rChannel   = fixture.getAddress() + 1;
        gChannel   = fixture.getAddress() + 2;
        bChannel   = fixture.getAddress() + 3;
    }
    
    void rgbChannels( int rChannel, int gChannel, int bChannel )
    {
       brgbwChannels( 0, rChannel, gChannel, bChannel, 0 );
    }
   
    void rgbwChannels( int rChannel, int gChannel, int bChannel, int wChannel)
    {
       brgbwChannels( 0, rChannel, gChannel, bChannel, wChannel );
    }
   
    void brgbwChannels( int briChannel, int rChannel, int gChannel, int bChannel, int wChannel )
    {
         this.briChannel    = briChannel;
         this.rChannel      = rChannel;
         this.gChannel      = gChannel;
         this.bChannel      = bChannel;
         this.wChannel      = wChannel;
    }
    
    void draw()
    {
       pushStyle();
       
       float bri = (briChannel == 0) ? 0 : fixture.getParam( briChannel -1 ).getValue();
       float r   = (rChannel   == 0) ? 0 : fixture.getParam( rChannel   -1 ).getValue() * ( bri / 255.0 );
       float g   = (gChannel   == 0) ? 0 : fixture.getParam( gChannel   -1 ).getValue() * ( bri / 255.0 );
       float b   = (bChannel   == 0) ? 0 : fixture.getParam( bChannel   -1 ).getValue() * ( bri / 255.0 );
       //float w   = (wChannel == 0) ? 0 : fixture.getParam( wChannel   ).getValue() * ( bri / 255.0 );
       
       fill( r, g, b );
       ellipse( xPos, yPos, xSize, ySize );
       
       popStyle();
    }
    
    private int briChannel;
    private int rChannel;
    private int gChannel;
    private int bChannel;
    private int wChannel;
}
