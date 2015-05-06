# Azure Active Directory Client for Java

Access the [Azure AD Graph API](https://msdn.microsoft.com/en-us/library/azure/hh974476.aspx) from Java including CRUD operations across the following entities:

* [Application](https://msdn.microsoft.com/en-us/library/azure/dn151677.aspx) (CRUD)
* [Contact](https://msdn.microsoft.com/en-us/library/azure/hh974479.aspx) (RUD)
* [Device](https://msdn.microsoft.com/en-us/library/azure/dn151674.aspx) (CRUD)
* [DirectoryRole](https://msdn.microsoft.com/en-us/library/azure/jj134103.aspx) (R)
* [Group](https://msdn.microsoft.com/en-us/library/azure/hh974486.aspx) (CRUD)
* [OAuth2PermissionGrant](https://msdn.microsoft.com/en-us/library/azure/dn151672.aspx) (CRUD)
* [ServicePrincipal](https://msdn.microsoft.com/en-us/library/azure/dn194452.aspx) (CRUD)
* [SubscribedSku](https://msdn.microsoft.com/en-us/library/azure/jj134104.aspx) (R)
* [TenantDetail](https://msdn.microsoft.com/en-us/library/azure/hh974467.aspx) (RU)
* [User](https://msdn.microsoft.com/en-us/library/azure/hh974483.aspx) (CRUD)

## Dependencies

* [Azure Active Directory Authentication for Java](https://github.com/AzureAD/azure-activedirectory-library-for-java)
* [Apache Commons Lang](https://commons.apache.org/proper/commons-lang/)
* [Apache HttpComponents](https://hc.apache.org/)
* [FlexJson](http://flexjson.sourceforge.net/)
* [JUnit](http://junit.org/) (Testing)
* [Mockito](http://mockito.org/) (Mocking)

## Notes

The project currently includes a local Maven repo along with a custom build of [adal4j](https://github.com/AzureAD/azure-activedirectory-library-for-java). The source code for this custom fork is available here:

https://github.com/nwoolls/azure-activedirectory-library-for-java

This is temporary until our outstanding PRs are merged / addressed ([PR17](https://github.com/AzureAD/azure-activedirectory-library-for-java/pull/17), [PR19](https://github.com/AzureAD/azure-activedirectory-library-for-java/pull/19))
