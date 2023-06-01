# Kochsnowflake

# Running the program
the program can be compiled easily just by compiling it within an ide

# doubleTesting()
The constructor for the snowflake program that creates a frame and assigns it a panel for drawing and a panel for buttons

# setScale(double scale)
A method used during zooming that alters the scale of the image in order to create a zoom like effect, the value is called later for transformations of the shape to apply zoom

# paintComponent(graphics g)
A method used to create the kochsnowflake through the storesnow method and apply draw it using the g2d path method from the points given, as well as apply any needed transformations such as scale, colour, or panning

# newOrder()
A method that prompts users with a popup box for new kochsnowflakes of differing orders, order is default to previous order if the box is closed, order is defaulted to 0 if input is less than 0

# newColour()
A method that prompts users with an input text box that then checks to see if the colours inputed match the allowed red, blue, orange, green, yellow, black, white or pink, if it dosent it defaults to black, otherwise it recolours the snowflake in the given colour

# storeSnow(int order, double x1, double y1, double x5,double y5)
A method used to store x y coordinates within a an array using an algorithm based on equations found here https://mathworld.wolfram.com/KochSnowflake.html
the method works recursively, calling itself until the order reached is 0
the algorithm finds points by taking the difference between x1 and x5 and y1 and y5, and dividing it by 3 and adding 2 to find x2 and y2, as well as the square root of 3 multiplied the point1 and point5 difference/6 and then adding it to the half of x1 plus x5 and y1 plus y5. Such that it creates an offshoot triangle at the centre point that is not linked at the bottom
This has been updated to use doubles so that no point error occurs, as it was during int casting due to rounding

# mouseDragged(e)
A method that tracks of the distance the mouse has been dragged after zooming, the difference between this and the lastmouse postions x and y are then added onto panx and pany in order to allow for mouse panning along the frame following zooming. This is done in order to allow a look at the entire snowflake
