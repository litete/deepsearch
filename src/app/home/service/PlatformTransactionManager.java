package app.home.service;

import org.apache.ibatis.transaction.TransactionException;
import org.snaker.engine.access.transaction.TransactionStatus;
import org.springframework.transaction.TransactionDefinition;

public interface PlatformTransactionManager {
	//由TransactionDefinition得到TransactionStatus对象
TransactionStatus getTransactionStatus(TransactionDefinition definition) throws TransactionException;
//提交
Void commit(TransactionStatus status)throws TransactionException;
//回滚
Void roolback(TransactionStatus status)throws TransactionException;
}
