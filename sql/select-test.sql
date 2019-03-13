CONNECT 'jdbc:mysql://127.0.0.1:3306/hotel?createDatabaseIfNotExist=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&allowPublicKeyRetrieval=true';

SELECT * FROM users;
SELECT * FROM orders;

SELECT o.id, u.first_name, u.last_name, o.bill, s.name
	FROM users u, orders o, statuses s
	WHERE o.user_id=u.id AND o.status_id=s.id;

DISCONNECT;