package coolsquid.squidapi.util.interfaces;

public interface IValue {
	public abstract <E> E getObject();
	public abstract byte getByte();
	public abstract short getShort();
	public abstract int getInt();
	public abstract long getLong();
	public abstract float getFloat();
	public abstract double getDouble();
	public abstract boolean getBoolean();
	public abstract char getChar();
}