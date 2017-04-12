package edu.cad.daos;

import java.util.List;
import edu.cad.entities.interfaces.IDatabaseEntity;

public interface IDAO <T extends IDatabaseEntity>{
	
	public List<T> getAll();
	
	public T get(int id);
	
	public T update(T instance);
	
	public boolean create(T instance);
	
	public boolean delete(int id);
}
