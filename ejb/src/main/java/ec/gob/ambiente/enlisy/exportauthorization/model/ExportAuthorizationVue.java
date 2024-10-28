package ec.gob.ambiente.enlisy.exportauthorization.model;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Where;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the export_authorization_vue database table.
 * 
 */
@Entity
@Table(name="export_authorization_vue", schema="biodiversity")
@NamedQuery(name="ExportAuthorizationVue.findAll", query="SELECT e FROM ExportAuthorizationVue e")
@Where(clause = "eavu_status='t'")
public class ExportAuthorizationVue implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer eavuId;
	private String eavuAdminAuthorityAddress;
	private String eavuAdminAuthorityCountry;
	private String eavuAdminAuthorityName;
	private Boolean eavuAppendixIIi;
	private String eavuApplicantAddress;
	private String eavuApplicantCantonCode;
	private String eavuApplicantCantonName;
	private String eavuApplicantClassificName;
	private String eavuApplicantClassificatCod;
	private String eavuApplicantCompanyName;
	private String eavuApplicantEmail;
	private String eavuApplicantFax;
	private String eavuApplicantIdNumber;
	private String eavuApplicantIdTypeCatalog;
	private String eavuApplicantIdTypeName;
	private String eavuApplicantName;
	private String eavuApplicantParroqCode;
	private String eavuApplicantParroqName;
	private String eavuApplicantPhone;
	private String eavuApplicantCellPhone;
	private String eavuApplicantProvinceCode;
	private String eavuApplicantProvinceName;
	private String eavuBillLadingNumber;
	private Timestamp eavuBoardingDate;
	private String eavuBoardingPlaceCatalog;
	private String eavuBoardingPlaceName;
	private Timestamp eavuCertificExpirationDate;
	private String eavuCertificateCatalog;
	private Timestamp eavuCertificateIssueDate;
	private String eavuCertificateName;
	private String eavuCertificateNumber;
	private String eavuConveyanceCatalog;
	private String eavuConveyanceName;
	private String eavuConveyanceNumber;
	private Timestamp eavuDateCreate;
	private Timestamp eavuDateUpdate;
	private String eavuDescriptionSubject;
	private String eavuDocumentFunctionCatalog;
	private Boolean eavuExoticSpecies;
	private String eavuExporterAddress;
	private String eavuExporterCantonCode;
	private String eavuExporterCantonName;
	private String eavuExporterClassificatCode;
	private String eavuExporterClassificatName;
	private String eavuExporterCompanyName;
	private String eavuExporterCountryCode;
	private String eavuExporterCountryName;
	private String eavuExporterEmail;
	private String eavuExporterFax;
	private String eavuExporterIdNumber;
	private String eavuExporterIdTypeCatalog;
	private String eavuExporterIdTypeName;
	private String eavuExporterName;
	private String eavuExporterParroqCode;
	private String eavuExporterParroqName;
	private String eavuExporterCellPhone;
	private String eavuExporterPhone;
	private String eavuExporterProvinceCode;
	private String eavuExporterProvinceName;
	private String eavuImporterAddress;
	private String eavuImporterCantonCode;
	private String eavuImporterCantonName;
	private String eavuImporterClassificatCode;
	private String eavuImporterClassificatName;
	private String eavuImporterCountryCode;
	private String eavuImporterCountryName;
	private String eavuImporterEmail;
	private String eavuImporterFax;
	private String eavuImporterIdNumber;
	private String eavuImporterIdTypeCatalog;
	private String eavuImporterIdTypeName;
	private String eavuImporterName;
	private String eavuImporterCompanyName;
	private String eavuImporterParroqCode;
	private String eavuImporterParroqName;
	private String eavuImporterPhone;
	private String eavuImporterCellPhone;
	private String eavuImporterProvinceCode;
	private String eavuImporterProvinceName;
	private String eavuLevelApprovObservations;
	private Integer eavuLevelApproval;
	private Timestamp eavuRegisterDate;
	private String eavuRegisterId;
	private String eavuReportAuthorityCites;
	private String eavuRequestCatalog;
	private String eavuRequestCityCode;
	private String eavuRequestCityName;
	private Timestamp eavuRequestDate;
	private String eavuRequestName;
	private String eavuRequestNumber;
	private String eavuRequestTypeCatalog;
	private String eavuRequestTypeName;
	private String eavuSecurityStampNumber;
	private String eavuShipmentProduct;
	private String eavuSpecialConditions;
	private Boolean eavuStatus=true;
	private Integer eavuSuggestionTechBiodiver;
	private String eavuTechReportBiosecurity;
	private String eavuTotalQuantityProdUnit;
	private BigDecimal eavuTotalQuantityProduct;
	private String eavuTransactionObjCatalog;
	private String eavuTransactionObjDetail;
	private String eavuTransactionObjName;
	private String eavuTransportCompanyName;
	private Timestamp eavuUpdateDate;
	private String eavuUpdaterId;
	private String eavuUpdaterIp;
	private String eavuUse;
	private String eavuUserCreate;
	private String eavuUserUpdate;
	private String eavuCode;
	private Integer eavuStatusVue;
	private List<ExportAuthVueProduct> exportAuthVueProducts;

	private Integer eavuRequestProvinceId;
	private String eavuRequestProvinceCode;
	private String eavuRequestProvinceName;
	private Integer eavuApplicantProvinceId;
	private Integer eavuApplicantCantonId;
	private Integer eavuApplicantParroqId;
	private Integer eavuExporterProvinceId;
	private Integer eavuExporterCantonId;
	private Integer eavuExporterParroqId;
	private Integer eavuRequestTypeCatalogId;
	private Integer eavuTransactionObjCatalogId;
	private Integer eavuConveyanceCatalogId;
	private Integer eavuBoardingPlaceCatalogId;
	private Integer eavuShipmentProductCatalogId;
	private String eavuShipmentProductCatalog;
	private Integer eavuImporterCountryId;
	private Integer eavuInformationSourceId;
	private String eavuInformationSource;
	private Integer eavuExporterCountryId;
	private String eavuPatentNumber;

	private Integer eavuDestinationCityId;
	private Integer eavuPermitTypeId;
	private Boolean eavuPreconvention;

	private List<ExportAuthorizationVueDocumentNumber> exportAuthorizationVueDocumentNumbers;


	private String statusText;
	private String statusIcon;

	public ExportAuthorizationVue() {
	}


	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EXPORT_AUTHORIZATION_VUE_GENERATOR")
	@SequenceGenerator(name = "EXPORT_AUTHORIZATION_VUE_GENERATOR", initialValue = 1, sequenceName = "seq_eavu_id", schema = "biodiversity", allocationSize = 1)
	@Column(name="eavu_id")
	public Integer getEavuId() {
		return this.eavuId;
	}

	public void setEavuId(Integer eavuId) {
		this.eavuId = eavuId;
	}


	@Column(name="eavu_admin_authority_address")
	public String getEavuAdminAuthorityAddress() {
		return this.eavuAdminAuthorityAddress;
	}

	public void setEavuAdminAuthorityAddress(String eavuAdminAuthorityAddress) {
		this.eavuAdminAuthorityAddress = eavuAdminAuthorityAddress;
	}


	@Column(name="eavu_admin_authority_country")
	public String getEavuAdminAuthorityCountry() {
		return this.eavuAdminAuthorityCountry;
	}

	public void setEavuAdminAuthorityCountry(String eavuAdminAuthorityCountry) {
		this.eavuAdminAuthorityCountry = eavuAdminAuthorityCountry;
	}


	@Column(name="eavu_admin_authority_name")
	public String getEavuAdminAuthorityName() {
		return this.eavuAdminAuthorityName;
	}

	public void setEavuAdminAuthorityName(String eavuAdminAuthorityName) {
		this.eavuAdminAuthorityName = eavuAdminAuthorityName;
	}


	@Column(name="eavu_appendix_i_ii")
	public Boolean getEavuAppendixIIi() {
		return this.eavuAppendixIIi;
	}

	public void setEavuAppendixIIi(Boolean eavuAppendixIIi) {
		this.eavuAppendixIIi = eavuAppendixIIi;
	}


	@Column(name="eavu_applicant_address")
	public String getEavuApplicantAddress() {
		return this.eavuApplicantAddress;
	}

	public void setEavuApplicantAddress(String eavuApplicantAddress) {
		this.eavuApplicantAddress = eavuApplicantAddress;
	}


	@Column(name="eavu_applicant_canton_code")
	public String getEavuApplicantCantonCode() {
		return this.eavuApplicantCantonCode;
	}

	public void setEavuApplicantCantonCode(String eavuApplicantCantonCode) {
		this.eavuApplicantCantonCode = eavuApplicantCantonCode;
	}


	@Column(name="eavu_applicant_canton_name")
	public String getEavuApplicantCantonName() {
		return this.eavuApplicantCantonName;
	}

	public void setEavuApplicantCantonName(String eavuApplicantCantonName) {
		this.eavuApplicantCantonName = eavuApplicantCantonName;
	}


	@Column(name="eavu_applicant_classific_name")
	public String getEavuApplicantClassificName() {
		return this.eavuApplicantClassificName;
	}

	public void setEavuApplicantClassificName(String eavuApplicantClassificName) {
		this.eavuApplicantClassificName = eavuApplicantClassificName;
	}


	@Column(name="eavu_applicant_classificat_cod")
	public String getEavuApplicantClassificatCod() {
		return this.eavuApplicantClassificatCod;
	}

	public void setEavuApplicantClassificatCod(String eavuApplicantClassificatCod) {
		this.eavuApplicantClassificatCod = eavuApplicantClassificatCod;
	}


	@Column(name="eavu_applicant_company_name")
	public String getEavuApplicantCompanyName() {
		return this.eavuApplicantCompanyName;
	}

	public void setEavuApplicantCompanyName(String eavuApplicantCompanyName) {
		this.eavuApplicantCompanyName = eavuApplicantCompanyName;
	}


	@Column(name="eavu_applicant_email")
	public String getEavuApplicantEmail() {
		return this.eavuApplicantEmail;
	}

	public void setEavuApplicantEmail(String eavuApplicantEmail) {
		this.eavuApplicantEmail = eavuApplicantEmail;
	}


	@Column(name="eavu_applicant_fax")
	public String getEavuApplicantFax() {
		return this.eavuApplicantFax;
	}

	public void setEavuApplicantFax(String eavuApplicantFax) {
		this.eavuApplicantFax = eavuApplicantFax;
	}


	@Column(name="eavu_applicant_id_number")
	public String getEavuApplicantIdNumber() {
		return this.eavuApplicantIdNumber;
	}

	public void setEavuApplicantIdNumber(String eavuApplicantIdNumber) {
		this.eavuApplicantIdNumber = eavuApplicantIdNumber;
	}


	@Column(name="eavu_applicant_id_type_catalog")
	public String getEavuApplicantIdTypeCatalog() {
		return this.eavuApplicantIdTypeCatalog;
	}

	public void setEavuApplicantIdTypeCatalog(String eavuApplicantIdTypeCatalog) {
		this.eavuApplicantIdTypeCatalog = eavuApplicantIdTypeCatalog;
	}


	@Column(name="eavu_applicant_id_type_name")
	public String getEavuApplicantIdTypeName() {
		return this.eavuApplicantIdTypeName;
	}

	public void setEavuApplicantIdTypeName(String eavuApplicantIdTypeName) {
		this.eavuApplicantIdTypeName = eavuApplicantIdTypeName;
	}


	@Column(name="eavu_applicant_name")
	public String getEavuApplicantName() {
		return this.eavuApplicantName;
	}

	public void setEavuApplicantName(String eavuApplicantName) {
		this.eavuApplicantName = eavuApplicantName;
	}


	@Column(name="eavu_applicant_parroq_code")
	public String getEavuApplicantParroqCode() {
		return this.eavuApplicantParroqCode;
	}

	public void setEavuApplicantParroqCode(String eavuApplicantParroqCode) {
		this.eavuApplicantParroqCode = eavuApplicantParroqCode;
	}


	@Column(name="eavu_applicant_parroq_name")
	public String getEavuApplicantParroqName() {
		return this.eavuApplicantParroqName;
	}

	public void setEavuApplicantParroqName(String eavuApplicantParroqName) {
		this.eavuApplicantParroqName = eavuApplicantParroqName;
	}


	@Column(name="eavu_applicant_phone")
	public String getEavuApplicantPhone() {
		return this.eavuApplicantPhone;
	}

	public void setEavuApplicantPhone(String eavuApplicantPhone) {
		this.eavuApplicantPhone = eavuApplicantPhone;
	}

	@Column(name="eavu_applicant_cell_phone")
	public String getEavuApplicantCellPhone() {
		return this.eavuApplicantCellPhone;
	}

	public void setEavuApplicantCellPhone(String eavuApplicantCellPhone) {
		this.eavuApplicantCellPhone = eavuApplicantCellPhone;
	}


	@Column(name="eavu_applicant_province_code")
	public String getEavuApplicantProvinceCode() {
		return this.eavuApplicantProvinceCode;
	}

	public void setEavuApplicantProvinceCode(String eavuApplicantProvinceCode) {
		this.eavuApplicantProvinceCode = eavuApplicantProvinceCode;
	}


	@Column(name="eavu_applicant_province_name")
	public String getEavuApplicantProvinceName() {
		return this.eavuApplicantProvinceName;
	}

	public void setEavuApplicantProvinceName(String eavuApplicantProvinceName) {
		this.eavuApplicantProvinceName = eavuApplicantProvinceName;
	}


	@Column(name="eavu_bill_lading_number")
	public String getEavuBillLadingNumber() {
		return this.eavuBillLadingNumber;
	}

	public void setEavuBillLadingNumber(String eavuBillLadingNumber) {
		this.eavuBillLadingNumber = eavuBillLadingNumber;
	}


	@Column(name="eavu_boarding_date")
	public Timestamp getEavuBoardingDate() {
		return this.eavuBoardingDate;
	}

	public void setEavuBoardingDate(Timestamp eavuBoardingDate) {
		this.eavuBoardingDate = eavuBoardingDate;
	}


	@Column(name="eavu_boarding_place_catalog")
	public String getEavuBoardingPlaceCatalog() {
		return this.eavuBoardingPlaceCatalog;
	}

	public void setEavuBoardingPlaceCatalog(String eavuBoardingPlaceCatalog) {
		this.eavuBoardingPlaceCatalog = eavuBoardingPlaceCatalog;
	}


	@Column(name="eavu_boarding_place_name")
	public String getEavuBoardingPlaceName() {
		return this.eavuBoardingPlaceName;
	}

	public void setEavuBoardingPlaceName(String eavuBoardingPlaceName) {
		this.eavuBoardingPlaceName = eavuBoardingPlaceName;
	}


	@Column(name="eavu_certific_expiration_date")
	public Timestamp getEavuCertificExpirationDate() {
		return this.eavuCertificExpirationDate;
	}

	public void setEavuCertificExpirationDate(Timestamp eavuCertificExpirationDate) {
		this.eavuCertificExpirationDate = eavuCertificExpirationDate;
	}


	@Column(name="eavu_certificate_catalog")
	public String getEavuCertificateCatalog() {
		return this.eavuCertificateCatalog;
	}

	public void setEavuCertificateCatalog(String eavuCertificateCatalog) {
		this.eavuCertificateCatalog = eavuCertificateCatalog;
	}


	@Column(name="eavu_certificate_issue_date")
	public Timestamp getEavuCertificateIssueDate() {
		return this.eavuCertificateIssueDate;
	}

	public void setEavuCertificateIssueDate(Timestamp eavuCertificateIssueDate) {
		this.eavuCertificateIssueDate = eavuCertificateIssueDate;
	}


	@Column(name="eavu_certificate_name")
	public String getEavuCertificateName() {
		return this.eavuCertificateName;
	}

	public void setEavuCertificateName(String eavuCertificateName) {
		this.eavuCertificateName = eavuCertificateName;
	}


	@Column(name="eavu_certificate_number")
	public String getEavuCertificateNumber() {
		return this.eavuCertificateNumber;
	}

	public void setEavuCertificateNumber(String eavuCertificateNumber) {
		this.eavuCertificateNumber = eavuCertificateNumber;
	}


	@Column(name="eavu_conveyance_catalog")
	public String getEavuConveyanceCatalog() {
		return this.eavuConveyanceCatalog;
	}

	public void setEavuConveyanceCatalog(String eavuConveyanceCatalog) {
		this.eavuConveyanceCatalog = eavuConveyanceCatalog;
	}


	@Column(name="eavu_conveyance_name")
	public String getEavuConveyanceName() {
		return this.eavuConveyanceName;
	}

	public void setEavuConveyanceName(String eavuConveyanceName) {
		this.eavuConveyanceName = eavuConveyanceName;
	}


	@Column(name="eavu_conveyance_number")
	public String getEavuConveyanceNumber() {
		return this.eavuConveyanceNumber;
	}

	public void setEavuConveyanceNumber(String eavuConveyanceNumber) {
		this.eavuConveyanceNumber = eavuConveyanceNumber;
	}


	@Column(name="eavu_date_create")
	public Timestamp getEavuDateCreate() {
		return this.eavuDateCreate;
	}

	public void setEavuDateCreate(Timestamp eavuDateCreate) {
		this.eavuDateCreate = eavuDateCreate;
	}


	@Column(name="eavu_date_update")
	public Timestamp getEavuDateUpdate() {
		return this.eavuDateUpdate;
	}

	public void setEavuDateUpdate(Timestamp eavuDateUpdate) {
		this.eavuDateUpdate = eavuDateUpdate;
	}


	@Column(name="eavu_description_subject")
	public String getEavuDescriptionSubject() {
		return this.eavuDescriptionSubject;
	}

	public void setEavuDescriptionSubject(String eavuDescriptionSubject) {
		this.eavuDescriptionSubject = eavuDescriptionSubject;
	}


	@Column(name="eavu_document_function_catalog")
	public String getEavuDocumentFunctionCatalog() {
		return this.eavuDocumentFunctionCatalog;
	}

	public void setEavuDocumentFunctionCatalog(String eavuDocumentFunctionCatalog) {
		this.eavuDocumentFunctionCatalog = eavuDocumentFunctionCatalog;
	}


	@Column(name="eavu_exotic_species")
	public Boolean getEavuExoticSpecies() {
		return this.eavuExoticSpecies;
	}

	public void setEavuExoticSpecies(Boolean eavuExoticSpecies) {
		this.eavuExoticSpecies = eavuExoticSpecies;
	}


	@Column(name="eavu_exporter_address")
	public String getEavuExporterAddress() {
		return this.eavuExporterAddress;
	}

	public void setEavuExporterAddress(String eavuExporterAddress) {
		this.eavuExporterAddress = eavuExporterAddress;
	}


	@Column(name="eavu_exporter_canton_code")
	public String getEavuExporterCantonCode() {
		return this.eavuExporterCantonCode;
	}

	public void setEavuExporterCantonCode(String eavuExporterCantonCode) {
		this.eavuExporterCantonCode = eavuExporterCantonCode;
	}


	@Column(name="eavu_exporter_canton_name")
	public String getEavuExporterCantonName() {
		return this.eavuExporterCantonName;
	}

	public void setEavuExporterCantonName(String eavuExporterCantonName) {
		this.eavuExporterCantonName = eavuExporterCantonName;
	}


	@Column(name="eavu_exporter_classificat_code")
	public String getEavuExporterClassificatCode() {
		return this.eavuExporterClassificatCode;
	}

	public void setEavuExporterClassificatCode(String eavuExporterClassificatCode) {
		this.eavuExporterClassificatCode = eavuExporterClassificatCode;
	}


	@Column(name="eavu_exporter_classificat_name")
	public String getEavuExporterClassificatName() {
		return this.eavuExporterClassificatName;
	}

	public void setEavuExporterClassificatName(String eavuExporterClassificatName) {
		this.eavuExporterClassificatName = eavuExporterClassificatName;
	}


	@Column(name="eavu_exporter_company_name")
	public String getEavuExporterCompanyName() {
		return this.eavuExporterCompanyName;
	}

	public void setEavuExporterCompanyName(String eavuExporterCompanyName) {
		this.eavuExporterCompanyName = eavuExporterCompanyName;
	}




	@Column(name="eavu_exporter_country_code")
	public String getEavuExporterCountryCode() {
		return this.eavuExporterCountryCode;
	}

	public void setEavuExporterCountryCode(String eavuExporterCountryCode) {
		this.eavuExporterCountryCode = eavuExporterCountryCode;
	}


	@Column(name="eavu_exporter_country_name")
	public String getEavuExporterCountryName() {
		return this.eavuExporterCountryName;
	}

	public void setEavuExporterCountryName(String eavuExporterCountryName) {
		this.eavuExporterCountryName = eavuExporterCountryName;
	}


	@Column(name="eavu_exporter_email")
	public String getEavuExporterEmail() {
		return this.eavuExporterEmail;
	}

	public void setEavuExporterEmail(String eavuExporterEmail) {
		this.eavuExporterEmail = eavuExporterEmail;
	}


	@Column(name="eavu_exporter_fax")
	public String getEavuExporterFax() {
		return this.eavuExporterFax;
	}

	public void setEavuExporterFax(String eavuExporterFax) {
		this.eavuExporterFax = eavuExporterFax;
	}


	@Column(name="eavu_exporter_id_number")
	public String getEavuExporterIdNumber() {
		return this.eavuExporterIdNumber;
	}

	public void setEavuExporterIdNumber(String eavuExporterIdNumber) {
		this.eavuExporterIdNumber = eavuExporterIdNumber;
	}


	@Column(name="eavu_exporter_id_type_catalog")
	public String getEavuExporterIdTypeCatalog() {
		return this.eavuExporterIdTypeCatalog;
	}

	public void setEavuExporterIdTypeCatalog(String eavuExporterIdTypeCatalog) {
		this.eavuExporterIdTypeCatalog = eavuExporterIdTypeCatalog;
	}


	@Column(name="eavu_exporter_id_type_name")
	public String getEavuExporterIdTypeName() {
		return this.eavuExporterIdTypeName;
	}

	public void setEavuExporterIdTypeName(String eavuExporterIdTypeName) {
		this.eavuExporterIdTypeName = eavuExporterIdTypeName;
	}


	@Column(name="eavu_exporter_name")
	public String getEavuExporterName() {
		return this.eavuExporterName;
	}

	public void setEavuExporterName(String eavuExporterName) {
		this.eavuExporterName = eavuExporterName;
	}


	@Column(name="eavu_exporter_parroq_code")
	public String getEavuExporterParroqCode() {
		return this.eavuExporterParroqCode;
	}

	public void setEavuExporterParroqCode(String eavuExporterParroqCode) {
		this.eavuExporterParroqCode = eavuExporterParroqCode;
	}


	@Column(name="eavu_exporter_parroq_name")
	public String getEavuExporterParroqName() {
		return this.eavuExporterParroqName;
	}

	public void setEavuExporterParroqName(String eavuExporterParroqName) {
		this.eavuExporterParroqName = eavuExporterParroqName;
	}


	@Column(name="eavu_exporter_phone")
	public String getEavuExporterPhone() {
		return this.eavuExporterPhone;
	}

	public void setEavuExporterPhone(String eavuExporterPhone) {
		this.eavuExporterPhone = eavuExporterPhone;
	}

	@Column(name="eavu_exporter_cell_phone")
	public String getEavuExporterCellPhone() {
		return this.eavuExporterCellPhone;
	}

	public void setEavuExporterCellPhone(String eavuExporterCellPhone) {
		this.eavuExporterCellPhone = eavuExporterCellPhone;
	}

	@Column(name="eavu_exporter_province_code")
	public String getEavuExporterProvinceCode() {
		return this.eavuExporterProvinceCode;
	}

	public void setEavuExporterProvinceCode(String eavuExporterProvinceCode) {
		this.eavuExporterProvinceCode = eavuExporterProvinceCode;
	}


	@Column(name="eavu_exporter_province_name")
	public String getEavuExporterProvinceName() {
		return this.eavuExporterProvinceName;
	}

	public void setEavuExporterProvinceName(String eavuExporterProvinceName) {
		this.eavuExporterProvinceName = eavuExporterProvinceName;
	}


	@Column(name="eavu_importer_address")
	public String getEavuImporterAddress() {
		return this.eavuImporterAddress;
	}

	public void setEavuImporterAddress(String eavuImporterAddress) {
		this.eavuImporterAddress = eavuImporterAddress;
	}


	@Column(name="eavu_importer_canton_code")
	public String getEavuImporterCantonCode() {
		return this.eavuImporterCantonCode;
	}

	public void setEavuImporterCantonCode(String eavuImporterCantonCode) {
		this.eavuImporterCantonCode = eavuImporterCantonCode;
	}


	@Column(name="eavu_importer_canton_name")
	public String getEavuImporterCantonName() {
		return this.eavuImporterCantonName;
	}

	public void setEavuImporterCantonName(String eavuImporterCantonName) {
		this.eavuImporterCantonName = eavuImporterCantonName;
	}


	@Column(name="eavu_importer_classificat_code")
	public String getEavuImporterClassificatCode() {
		return this.eavuImporterClassificatCode;
	}

	public void setEavuImporterClassificatCode(String eavuImporterClassificatCode) {
		this.eavuImporterClassificatCode = eavuImporterClassificatCode;
	}


	@Column(name="eavu_importer_classificat_name")
	public String getEavuImporterClassificatName() {
		return this.eavuImporterClassificatName;
	}

	public void setEavuImporterClassificatName(String eavuImporterClassificatName) {
		this.eavuImporterClassificatName = eavuImporterClassificatName;
	}


	@Column(name="eavu_importer_country_code")
	public String getEavuImporterCountryCode() {
		return this.eavuImporterCountryCode;
	}

	public void setEavuImporterCountryCode(String eavuImporterCountryCode) {
		this.eavuImporterCountryCode = eavuImporterCountryCode;
	}


	@Column(name="eavu_importer_country_name")
	public String getEavuImporterCountryName() {
		return this.eavuImporterCountryName;
	}

	public void setEavuImporterCountryName(String eavuImporterCountryName) {
		this.eavuImporterCountryName = eavuImporterCountryName;
	}


	@Column(name="eavu_importer_email")
	public String getEavuImporterEmail() {
		return this.eavuImporterEmail;
	}

	public void setEavuImporterEmail(String eavuImporterEmail) {
		this.eavuImporterEmail = eavuImporterEmail;
	}


	@Column(name="eavu_importer_fax")
	public String getEavuImporterFax() {
		return this.eavuImporterFax;
	}

	public void setEavuImporterFax(String eavuImporterFax) {
		this.eavuImporterFax = eavuImporterFax;
	}


	@Column(name="eavu_importer_id_number")
	public String getEavuImporterIdNumber() {
		return this.eavuImporterIdNumber;
	}

	public void setEavuImporterIdNumber(String eavuImporterIdNumber) {
		this.eavuImporterIdNumber = eavuImporterIdNumber;
	}


	@Column(name="eavu_importer_id_type_catalog")
	public String getEavuImporterIdTypeCatalog() {
		return this.eavuImporterIdTypeCatalog;
	}

	public void setEavuImporterIdTypeCatalog(String eavuImporterIdTypeCatalog) {
		this.eavuImporterIdTypeCatalog = eavuImporterIdTypeCatalog;
	}


	@Column(name="eavu_importer_id_type_name")
	public String getEavuImporterIdTypeName() {
		return this.eavuImporterIdTypeName;
	}

	public void setEavuImporterIdTypeName(String eavuImporterIdTypeName) {
		this.eavuImporterIdTypeName = eavuImporterIdTypeName;
	}


	@Column(name="eavu_importer_name")
	public String getEavuImporterName() {
		return this.eavuImporterName;
	}

	public void setEavuImporterName(String eavuImporterName) {
		this.eavuImporterName = eavuImporterName;
	}


	@Column(name="eavu_importer_company_name")
	public String getEavuImporterCompanyName() {
		return this.eavuImporterCompanyName;
	}

	public void setEavuImporterCompanyName(String eavuImporterCompanyName) {
		this.eavuImporterCompanyName = eavuImporterCompanyName;
	}

	@Column(name="eavu_importer_parroq_code")
	public String getEavuImporterParroqCode() {
		return this.eavuImporterParroqCode;
	}

	public void setEavuImporterParroqCode(String eavuImporterParroqCode) {
		this.eavuImporterParroqCode = eavuImporterParroqCode;
	}


	@Column(name="eavu_importer_parroq_name")
	public String getEavuImporterParroqName() {
		return this.eavuImporterParroqName;
	}

	public void setEavuImporterParroqName(String eavuImporterParroqName) {
		this.eavuImporterParroqName = eavuImporterParroqName;
	}


	@Column(name="eavu_importer_phone")
	public String getEavuImporterPhone() {
		return this.eavuImporterPhone;
	}

	public void setEavuImporterPhone(String eavuImporterPhone) {
		this.eavuImporterPhone = eavuImporterPhone;
	}

	@Column(name="eavu_importer_cell_phone")
	public String getEavuImporterCellPhone() {
		return this.eavuImporterCellPhone;
	}

	public void setEavuImporterCellPhone(String eavuImporterCellPhone) {
		this.eavuImporterCellPhone = eavuImporterCellPhone;
	}

	@Column(name="eavu_importer_province_code")
	public String getEavuImporterProvinceCode() {
		return this.eavuImporterProvinceCode;
	}

	public void setEavuImporterProvinceCode(String eavuImporterProvinceCode) {
		this.eavuImporterProvinceCode = eavuImporterProvinceCode;
	}


	@Column(name="eavu_importer_province_name")
	public String getEavuImporterProvinceName() {
		return this.eavuImporterProvinceName;
	}

	public void setEavuImporterProvinceName(String eavuImporterProvinceName) {
		this.eavuImporterProvinceName = eavuImporterProvinceName;
	}


	@Column(name="eavu_level_approv_observations")
	public String getEavuLevelApprovObservations() {
		return this.eavuLevelApprovObservations;
	}

	public void setEavuLevelApprovObservations(String eavuLevelApprovObservations) {
		this.eavuLevelApprovObservations = eavuLevelApprovObservations;
	}


	@Column(name="eavu_level_approval")
	public Integer getEavuLevelApproval() {
		return this.eavuLevelApproval;
	}

	public void setEavuLevelApproval(Integer eavuLevelApproval) {
		this.eavuLevelApproval = eavuLevelApproval;
	}


	@Column(name="eavu_register_date")
	public Timestamp getEavuRegisterDate() {
		return this.eavuRegisterDate;
	}

	public void setEavuRegisterDate(Timestamp eavuRegisterDate) {
		this.eavuRegisterDate = eavuRegisterDate;
	}


	@Column(name="eavu_register_id")
	public String getEavuRegisterId() {
		return this.eavuRegisterId;
	}

	public void setEavuRegisterId(String eavuRegisterId) {
		this.eavuRegisterId = eavuRegisterId;
	}


	@Column(name="eavu_report_authority_cites")
	public String getEavuReportAuthorityCites() {
		return this.eavuReportAuthorityCites;
	}

	public void setEavuReportAuthorityCites(String eavuReportAuthorityCites) {
		this.eavuReportAuthorityCites = eavuReportAuthorityCites;
	}


	@Column(name="eavu_request_catalog")
	public String getEavuRequestCatalog() {
		return this.eavuRequestCatalog;
	}

	public void setEavuRequestCatalog(String eavuRequestCatalog) {
		this.eavuRequestCatalog = eavuRequestCatalog;
	}


	@Column(name="eavu_request_city_code")
	public String getEavuRequestCityCode() {
		return this.eavuRequestCityCode;
	}

	public void setEavuRequestCityCode(String eavuRequestCityCode) {
		this.eavuRequestCityCode = eavuRequestCityCode;
	}


	@Column(name="eavu_request_city_name")
	public String getEavuRequestCityName() {
		return this.eavuRequestCityName;
	}

	public void setEavuRequestCityName(String eavuRequestCityName) {
		this.eavuRequestCityName = eavuRequestCityName;
	}


	@Column(name="eavu_request_date")
	public Timestamp getEavuRequestDate() {
		return this.eavuRequestDate;
	}

	public void setEavuRequestDate(Timestamp eavuRequestDate) {
		this.eavuRequestDate = eavuRequestDate;
	}


	@Column(name="eavu_request_name")
	public String getEavuRequestName() {
		return this.eavuRequestName;
	}

	public void setEavuRequestName(String eavuRequestName) {
		this.eavuRequestName = eavuRequestName;
	}


	@Column(name="eavu_request_number")
	public String getEavuRequestNumber() {
		return this.eavuRequestNumber;
	}

	public void setEavuRequestNumber(String eavuRequestNumber) {
		this.eavuRequestNumber = eavuRequestNumber;
	}


	@Column(name="eavu_request_type_catalog")
	public String getEavuRequestTypeCatalog() {
		return this.eavuRequestTypeCatalog;
	}

	public void setEavuRequestTypeCatalog(String eavuRequestTypeCatalog) {
		this.eavuRequestTypeCatalog = eavuRequestTypeCatalog;
	}


	@Column(name="eavu_request_type_name")
	public String getEavuRequestTypeName() {
		return this.eavuRequestTypeName;
	}

	public void setEavuRequestTypeName(String eavuRequestTypeName) {
		this.eavuRequestTypeName = eavuRequestTypeName;
	}


	@Column(name="eavu_security_stamp_number")
	public String getEavuSecurityStampNumber() {
		return this.eavuSecurityStampNumber;
	}

	public void setEavuSecurityStampNumber(String eavuSecurityStampNumber) {
		this.eavuSecurityStampNumber = eavuSecurityStampNumber;
	}


	@Column(name="eavu_shipment_product")
	public String getEavuShipmentProduct() {
		return this.eavuShipmentProduct;
	}

	public void setEavuShipmentProduct(String eavuShipmentProduct) {
		this.eavuShipmentProduct = eavuShipmentProduct;
	}


	@Column(name="eavu_special_conditions")
	public String getEavuSpecialConditions() {
		return this.eavuSpecialConditions;
	}

	public void setEavuSpecialConditions(String eavuSpecialConditions) {
		this.eavuSpecialConditions = eavuSpecialConditions;
	}


	@Column(name="eavu_status")
	public Boolean getEavuStatus() {
		return this.eavuStatus;
	}

	public void setEavuStatus(Boolean eavuStatus) {
		this.eavuStatus = eavuStatus;
	}


	@Column(name="eavu_suggestion_tech_biodiver")
	public Integer getEavuSuggestionTechBiodiver() {
		return this.eavuSuggestionTechBiodiver;
	}

	public void setEavuSuggestionTechBiodiver(Integer eavuSuggestionTechBiodiver) {
		this.eavuSuggestionTechBiodiver = eavuSuggestionTechBiodiver;
	}


	@Column(name="eavu_tech_report_biosecurity")
	public String getEavuTechReportBiosecurity() {
		return this.eavuTechReportBiosecurity;
	}

	public void setEavuTechReportBiosecurity(String eavuTechReportBiosecurity) {
		this.eavuTechReportBiosecurity = eavuTechReportBiosecurity;
	}


	@Column(name="eavu_total_quantity_prod_unit")
	public String getEavuTotalQuantityProdUnit() {
		return this.eavuTotalQuantityProdUnit;
	}

	public void setEavuTotalQuantityProdUnit(String eavuTotalQuantityProdUnit) {
		this.eavuTotalQuantityProdUnit = eavuTotalQuantityProdUnit;
	}


	@Column(name="eavu_total_quantity_product")
	public BigDecimal getEavuTotalQuantityProduct() {
		return this.eavuTotalQuantityProduct;
	}

	public void setEavuTotalQuantityProduct(BigDecimal eavuTotalQuantityProduct) {
		this.eavuTotalQuantityProduct = eavuTotalQuantityProduct;
	}


	@Column(name="eavu_transaction_obj_catalog")
	public String getEavuTransactionObjCatalog() {
		return this.eavuTransactionObjCatalog;
	}

	public void setEavuTransactionObjCatalog(String eavuTransactionObjCatalog) {
		this.eavuTransactionObjCatalog = eavuTransactionObjCatalog;
	}


	@Column(name="eavu_transaction_obj_detail")
	public String getEavuTransactionObjDetail() {
		return this.eavuTransactionObjDetail;
	}

	public void setEavuTransactionObjDetail(String eavuTransactionObjDetail) {
		this.eavuTransactionObjDetail = eavuTransactionObjDetail;
	}


	@Column(name="eavu_transaction_obj_name")
	public String getEavuTransactionObjName() {
		return this.eavuTransactionObjName;
	}

	public void setEavuTransactionObjName(String eavuTransactionObjName) {
		this.eavuTransactionObjName = eavuTransactionObjName;
	}


	@Column(name="eavu_transport_company_name")
	public String getEavuTransportCompanyName() {
		return this.eavuTransportCompanyName;
	}

	public void setEavuTransportCompanyName(String eavuTransportCompanyName) {
		this.eavuTransportCompanyName = eavuTransportCompanyName;
	}


	@Column(name="eavu_update_date")
	public Timestamp getEavuUpdateDate() {
		return this.eavuUpdateDate;
	}

	public void setEavuUpdateDate(Timestamp eavuUpdateDate) {
		this.eavuUpdateDate = eavuUpdateDate;
	}


	@Column(name="eavu_updater_id")
	public String getEavuUpdaterId() {
		return this.eavuUpdaterId;
	}

	public void setEavuUpdaterId(String eavuUpdaterId) {
		this.eavuUpdaterId = eavuUpdaterId;
	}


	@Column(name="eavu_updater_ip")
	public String getEavuUpdaterIp() {
		return this.eavuUpdaterIp;
	}

	public void setEavuUpdaterIp(String eavuUpdaterIp) {
		this.eavuUpdaterIp = eavuUpdaterIp;
	}


	@Column(name="eavu_use")
	public String getEavuUse() {
		return this.eavuUse;
	}

	public void setEavuUse(String eavuUse) {
		this.eavuUse = eavuUse;
	}


	@Column(name="eavu_user_create")
	public String getEavuUserCreate() {
		return this.eavuUserCreate;
	}

	public void setEavuUserCreate(String eavuUserCreate) {
		this.eavuUserCreate = eavuUserCreate;
	}


	@Column(name="eavu_user_update")
	public String getEavuUserUpdate() {
		return this.eavuUserUpdate;
	}

	public void setEavuUserUpdate(String eavuUserUpdate) {
		this.eavuUserUpdate = eavuUserUpdate;
	}

	
	@Column(name="eavu_code")
	public String getEavuCode() {
		return this.eavuCode;
	}
	
	public void setEavuCode(String eavuCode) {
		this.eavuCode = eavuCode;
	}
	
	@Column(name="eavu_status_vue")
	public Integer getEavuStatusVue() {
		return eavuStatusVue;
	}


	public void setEavuStatusVue(Integer eavuStatusVue) {
		this.eavuStatusVue = eavuStatusVue;
	}


	//bi-directional many-to-one association to ExportAuthVueProduct
	@OneToMany(mappedBy="exportAuthorizationVue", fetch=FetchType.EAGER)
	@Where(clause = "eavp_status='t'")
	public List<ExportAuthVueProduct> getExportAuthVueProducts() {
		return this.exportAuthVueProducts;
	}

	public void setExportAuthVueProducts(List<ExportAuthVueProduct> exportAuthVueProducts) {
		this.exportAuthVueProducts = exportAuthVueProducts;
	}

	public ExportAuthVueProduct addExportAuthVueProduct(ExportAuthVueProduct exportAuthVueProduct) {
		getExportAuthVueProducts().add(exportAuthVueProduct);
		exportAuthVueProduct.setExportAuthorizationVue(this);

		return exportAuthVueProduct;
	}

	public ExportAuthVueProduct removeExportAuthVueProduct(ExportAuthVueProduct exportAuthVueProduct) {
		getExportAuthVueProducts().remove(exportAuthVueProduct);
		exportAuthVueProduct.setExportAuthorizationVue(null);

		return exportAuthVueProduct;
	}

	@Column(name="eavu_request_province_id")
	public Integer getEavuRequestProvinceId() {
		return eavuRequestProvinceId;
	}

	public void setEavuRequestProvinceId(Integer eavuRequestProvinceId) {
		this.eavuRequestProvinceId = eavuRequestProvinceId;
	}

	@Column(name="eavu_request_province_code")
	public String getEavuRequestProvinceCode() {
		return eavuRequestProvinceCode;
	}

	public void setEavuRequestProvinceCode(String eavuRequestProvinceCode) {
		this.eavuRequestProvinceCode = eavuRequestProvinceCode;
	}

	@Column(name="eavu_request_province_name")
	public String getEavuRequestProvinceName() {
		return eavuRequestProvinceName;
	}

	public void setEavuRequestProvinceName(String eavuRequestProvinceName) {
		this.eavuRequestProvinceName = eavuRequestProvinceName;
	}

	@Column(name="eavu_applicant_province_id")
	public Integer getEavuApplicantProvinceId() {
		return eavuApplicantProvinceId;
	}

	public void setEavuApplicantProvinceId(Integer eavuApplicantProvinceId) {
		this.eavuApplicantProvinceId = eavuApplicantProvinceId;
	}

	@Column(name="eavu_applicant_canton_id")
	public Integer getEavuApplicantCantonId() {
		return eavuApplicantCantonId;
	}

	public void setEavuApplicantCantonId(Integer eavuApplicantCantonId) {
		this.eavuApplicantCantonId = eavuApplicantCantonId;
	}

	@Column(name="eavu_applicant_parroq_id")
	public Integer getEavuApplicantParroqId() {
		return eavuApplicantParroqId;
	}

	public void setEavuApplicantParroqId(Integer eavuApplicantParroqId) {
		this.eavuApplicantParroqId = eavuApplicantParroqId;
	}

	@Column(name="eavu_exporter_province_id")
	public Integer getEavuExporterProvinceId() {
		return eavuExporterProvinceId;
	}

	public void setEavuExporterProvinceId(Integer eavuExporterProvinceId) {
		this.eavuExporterProvinceId = eavuExporterProvinceId;
	}

	@Column(name="eavu_exporter_canton_id")
	public Integer getEavuExporterCantonId() {
		return eavuExporterCantonId;
	}

	public void setEavuExporterCantonId(Integer eavuExporterCantonId) {
		this.eavuExporterCantonId = eavuExporterCantonId;
	}

	@Column(name="eavu_exporter_parroq_id")
	public Integer getEavuExporterParroqId() {
		return eavuExporterParroqId;
	}

	public void setEavuExporterParroqId(Integer eavuExporterParroqId) {
		this.eavuExporterParroqId = eavuExporterParroqId;
	}

	@Column(name="eavu_request_type_catalog_id")
	public Integer getEavuRequestTypeCatalogId() {
		return eavuRequestTypeCatalogId;
	}

	public void setEavuRequestTypeCatalogId(Integer eavuRequestTypeCatalogId) {
		this.eavuRequestTypeCatalogId = eavuRequestTypeCatalogId;
	}

	@Column(name="eavu_transaction_obj_catalog_id")
	public Integer getEavuTransactionObjCatalogId() {
		return eavuTransactionObjCatalogId;
	}

	public void setEavuTransactionObjCatalogId(Integer eavuTransactionObjCatalogId) {
		this.eavuTransactionObjCatalogId = eavuTransactionObjCatalogId;
	}

	@Column(name="eavu_conveyance_catalog_id")
	public Integer getEavuConveyanceCatalogId() {
		return eavuConveyanceCatalogId;
	}

	public void setEavuConveyanceCatalogId(Integer eavuConveyanceCatalogId) {
		this.eavuConveyanceCatalogId = eavuConveyanceCatalogId;
	}

	@Column(name="eavu_boarding_place_catalog_id")
	public Integer getEavuBoardingPlaceCatalogId() {
		return eavuBoardingPlaceCatalogId;
	}

	public void setEavuBoardingPlaceCatalogId(Integer eavuBoardingPlaceCatalogId) {
		this.eavuBoardingPlaceCatalogId = eavuBoardingPlaceCatalogId;
	}

	@Column(name="eavu_shipment_product_catalog_id")
	public Integer getEavuShipmentProductCatalogId() {
		return eavuShipmentProductCatalogId;
	}

	public void setEavuShipmentProductCatalogId(Integer eavuShipmentProductCatalogId) {
		this.eavuShipmentProductCatalogId = eavuShipmentProductCatalogId;
	}

	@Column(name="eavu_shipment_product_catalog")
	public String getEavuShipmentProductCatalog() {
		return eavuShipmentProductCatalog;
	}

	public void setEavuShipmentProductCatalog(String eavuShipmentProductCatalog) {
		this.eavuShipmentProductCatalog = eavuShipmentProductCatalog;
	}

	@Column(name="eavu_importer_country_id")
	public Integer getEavuImporterCountryId() {
		return eavuImporterCountryId;
	}

	public void setEavuImporterCountryId(Integer eavuImporterCountryId) {
		this.eavuImporterCountryId = eavuImporterCountryId;
	}

	@Column(name="eavu_information_source_id")
	public Integer getEavuInformationSourceId() {
		return eavuInformationSourceId;
	}

	public void setEavuInformationSourceId(Integer eavuInformationSourceId) {
		this.eavuInformationSourceId = eavuInformationSourceId;
	}

	@Column(name="eavu_information_source")
	public String getEavuInformationSource() {
		return eavuInformationSource;
	}

	public void setEavuInformationSource(String eavuInformationSource) {
		this.eavuInformationSource = eavuInformationSource;
	}

	@Column(name="eavu_exporter_country_id")
	public Integer getEavuExporterCountryId() {
		return eavuExporterCountryId;
	}

	public void setEavuExporterCountryId(Integer eavuExporterCountryId) {
		this.eavuExporterCountryId = eavuExporterCountryId;
	}

	@Column(name="eavu_patent_number")
	public String getEavuPatentNumber() {
		return eavuPatentNumber;
	}

	public void setEavuPatentNumber(String eavuPatentNumber) {
		this.eavuPatentNumber = eavuPatentNumber;
	}

	//bi-directional many-to-one association to ExportAuthVueProduct
	@OneToMany(mappedBy="exportAuthorizationVue" ,fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@Where(clause = "eadn_status='t'")
	public List<ExportAuthorizationVueDocumentNumber> getExportAuthorizationVueDocumentNumbers() {
		return this.exportAuthorizationVueDocumentNumbers;
	}

	public void setExportAuthorizationVueDocumentNumbers(List<ExportAuthorizationVueDocumentNumber> exportAuthorizationVueDocumentNumbers) {
		this.exportAuthorizationVueDocumentNumbers = exportAuthorizationVueDocumentNumbers;
	}

	public ExportAuthorizationVueDocumentNumber addExportAuthorizationVueDocumentNumber(ExportAuthorizationVueDocumentNumber exportAuthorizationVueDocumentNumber) {
		getExportAuthorizationVueDocumentNumbers().add(exportAuthorizationVueDocumentNumber);
		exportAuthorizationVueDocumentNumber.setExportAuthorizationVue(this);

		return exportAuthorizationVueDocumentNumber;
	}

	public ExportAuthorizationVueDocumentNumber removeExportAuthorizationVueDocumentNumber(ExportAuthorizationVueDocumentNumber exportAuthorizationVueDocumentNumber) {
		getExportAuthorizationVueDocumentNumbers().remove(exportAuthorizationVueDocumentNumber);
		exportAuthorizationVueDocumentNumber.setExportAuthorizationVue(null);

		return exportAuthorizationVueDocumentNumber;
	}

	@Column(name="eavu_destination_city_id")
	public Integer getEavuDestinationCityId() {
		return eavuDestinationCityId;
	}

	public void setEavuDestinationCityId(Integer eavuDestinationCityId) {
		this.eavuDestinationCityId = eavuDestinationCityId;
	}

	@Column(name="eavu_preconvention")
	public Boolean getEavuPreconvention() {
		return eavuPreconvention;
	}

	public void setEavuPreconvention(Boolean eavuPreconvention) {
		this.eavuPreconvention = eavuPreconvention;
	}

	@Column(name="eavu_permit_type_id")
	public Integer getEavuPermitTypeId() {
		return eavuPermitTypeId;
	}

	public void setEavuPermitTypeId(Integer eavuPermitTypeId) {
		this.eavuPermitTypeId = eavuPermitTypeId;
	}

	@Transient
	public String getStatusText() {
		return statusText;
	}

	@Transient
	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}

	@Transient
	public String getStatusIcon() {
		return statusIcon;
	}

	@Transient
	public void setStatusIcon(String statusIcon) {
		this.statusIcon = statusIcon;
	}
}