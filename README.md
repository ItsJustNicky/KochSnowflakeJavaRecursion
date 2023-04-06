# Kochsnowflake

#Running the program
the program can be compiled easily just by compiling it within an ide

#create()
A method used to create the inital UI used within the program

#creat(double inScale,int inOrder,boolean booling)
A method used to create later itterations of the UI with settings in tact for later use without need for rerunning the program

#paint(graphics g)
A method used to display the graphics on a canvas for use in displaying the kochsnowflake

#scaleInt(int in, double scale)
A method used to modify the scale of the lines drawn, giving a zoom like effect

#storeSnow(int order, int x1, int y1, int x5,int y5)
A method used to store x y coordinates within a an array using an algorithm based on equations found here https://mathworld.wolfram.com/KochSnowflake.html
the method works recursively, calling itself until the order reached is 0
the algorithm finds points by taking the difference between x1 and x5 and y1 and y5, and dividing it by 3 and adding 2 to find x2 and y2, as well as the square root of 3 multiplied the point1 and point5 difference/6 and then adding it to the half of x1 plus x5 and y1 plus y5. Such that it creates an offshoot triangle at the centre point that is not linked

#drawSnow(graphics g)
A method that uses javas drawline method to draw lines between the xy coords stored in the prior array

#actionPerformed(ActionEvent e)
Is a method used to create action listener events for each button
