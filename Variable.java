public class Variable
{
	private String name;
	private double value;
	
	public Variable (String nameLn, double valueLn)
	{
		name = nameLn;
		value = valueLn;
	}
	
	public Variable (String nameLn)
	{
		name = nameLn;
		value = 0; 
	}
	
	public void setValue(double valueLn)
	{
		value = valueLn;
	}
	
	public double getValue()
	{
		return value;
	}
	
	public String getName()
	{
		return name;
	}
}
