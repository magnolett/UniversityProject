package br.com.assertsistemas.service;

import java.io.Serializable;
import java.util.List;

public interface GenericService<T,ID> extends Serializable {

			public void insert(T t) throws Exception;
			
			public void update (T t) throws Exception;
			
			public void delete (T t) throws Exception;
			
			public T findById (ID id) throws Exception;
			
			public List<T> findAll () throws Exception;
	}
