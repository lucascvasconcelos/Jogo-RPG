package dao;

import java.util.List;

import com.db4o.query.Query;

import modelo.Conta;

public class DAOConta  extends DAO<Conta>{
	public Conta read (Object chave) {
		String usuario = (String) chave;
		Query q = manager.query();
		q.constrain(Conta.class);
		q.descend("usuario").constrain(usuario);
		List<Conta> resultados = q.execute();
		if (resultados.size()>0)
			return resultados.get(0);
		else
			return null;
	}
}
