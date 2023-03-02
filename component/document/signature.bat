openssl pkcs8 -inform DER -nocrypt -in platform.pk8 -out key
openssl pkcs12 -export -in platform.x509.pem -inkey key -out keystore.p12 -password pass:123456 -name AliasName 

if exist keystore.jks (
	keytool -importkeystore -srckeystore keystore.jks -srcstorepass 123456 -destkeystore keystore.jks -deststoretype pkcs12
	del keystore.jks.old
) else (
	keytool -importkeystore -deststorepass 123456 -destkeystore keystore.jks -srckeystore keystore.p12 -srcstoretype PKCS12 -srcstorepass 123456
)
del key
del keystore.p12