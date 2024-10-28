package ec.gob.ambiente.enlisy.exportauthorization.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Where;

import ec.gob.ambiente.enlisy.speciescatalog.model.SpecieTaxa;


/**
 * The persistent class for the export_auth_vue_products database table.
 * 
 */
@Entity
@Table(name="export_auth_vue_products", schema="biodiversity")
@NamedQuery(name="ExportAuthVueProduct.findAll", query="SELECT e FROM ExportAuthVueProduct e")
@Where(clause = "eavp_status='t'")
public class ExportAuthVueProduct implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer eavpId;
	private String eavpAppendixOriginNumber;
	private Timestamp eavpCertificateDate;
	private String eavpCertificateNumber;
	private String eavpCommonNameProduct;
	private String eavpCountryCode;
	private String eavpCountryName;
	private Timestamp eavpDateCreate;
	private Timestamp eavpDateUpdate;
	private String eavpEstablishmentNumber;
	private String eavpForestProdMobilGuide;
	private String eavpLicenseNumber;
	private Timestamp eavpReexportDate;
	private String eavpReexportLastCountry;
	private Timestamp eavpRegisterDate;
	private String eavpRegisterId;
	private BigDecimal eavpSampleAmount;
	private String eavpSampleAmountUnit;
	private String eavpSampleDescription;
	private String eavpScientificNameProduct;
	private String eavpSerialProduct;
	private String eavpSpeciesCode;
	private String eavpSpeciesName;
	private Boolean eavpStatus=true;
	private String eavpTariffSubheading;
	private String eavpTariffSubheadingDescrip;
	private BigDecimal eavpTotalAmountExport;
	private String eavpTotalAmountExportUnit;
	private BigDecimal eavpTotalAmountQuota;
	private String eavpTotalAmountQuotaUnit;
	private Timestamp eavpUpdateDate;
	private String eavpUpdaterId;
	private String eavpUpdaterIp;
	private String eavpUserCreate;
	private String eavpUserUpdate;
	private String eavpUtilization;
	private String eavuRequestNumber;
	private ExportAuthorizationVue exportAuthorizationVue;

	private Integer eavpDescriptionTermsId;
	private String eavpDescriptionTermsCatalog;
	private Integer eavpOriginCodeId;
	private String eavpOriginCodeIdCatalog;
	private Integer eavpQuantityUnitId;
	private String eavpQuantityUnitCatalog;
	private String eavpSpecieGui;

	private CommercialTermCites descriptionTerms;
	private OriginCites originCode;
	private SpecieTaxa specie;
	private BigDecimal amount;
	private String apendixOrigin;
	private UnitMeasurement unitMeasurement;
	private String amountText;

	private boolean statusApendix;

	public ExportAuthVueProduct() {
	}


	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EXPORT_AUTH_VUE_PRODUCT_GENERATOR")
	@SequenceGenerator(name = "EXPORT_AUTH_VUE_PRODUCT_GENERATOR", initialValue = 1, sequenceName = "seq_eavp_id", schema = "biodiversity", allocationSize = 1)
	@Column(name="eavp_id")
	public Integer getEavpId() {
		return this.eavpId;
	}

	public void setEavpId(Integer eavpId) {
		this.eavpId = eavpId;
	}


	@Column(name="eavp_appendix_origin_number")
	public String getEavpAppendixOriginNumber() {
		return this.eavpAppendixOriginNumber;
	}

	public void setEavpAppendixOriginNumber(String eavpAppendixOriginNumber) {
		this.eavpAppendixOriginNumber = eavpAppendixOriginNumber;
	}


	@Column(name="eavp_certificate_date")
	public Timestamp getEavpCertificateDate() {
		return this.eavpCertificateDate;
	}

	public void setEavpCertificateDate(Timestamp eavpCertificateDate) {
		this.eavpCertificateDate = eavpCertificateDate;
	}


	@Column(name="eavp_certificate_number")
	public String getEavpCertificateNumber() {
		return this.eavpCertificateNumber;
	}

	public void setEavpCertificateNumber(String eavpCertificateNumber) {
		this.eavpCertificateNumber = eavpCertificateNumber;
	}


	@Column(name="eavp_common_name_product")
	public String getEavpCommonNameProduct() {
		return this.eavpCommonNameProduct;
	}

	public void setEavpCommonNameProduct(String eavpCommonNameProduct) {
		this.eavpCommonNameProduct = eavpCommonNameProduct;
	}


	@Column(name="eavp_country_code")
	public String getEavpCountryCode() {
		return this.eavpCountryCode;
	}

	public void setEavpCountryCode(String eavpCountryCode) {
		this.eavpCountryCode = eavpCountryCode;
	}


	@Column(name="eavp_country_name")
	public String getEavpCountryName() {
		return this.eavpCountryName;
	}

	public void setEavpCountryName(String eavpCountryName) {
		this.eavpCountryName = eavpCountryName;
	}


	@Column(name="eavp_date_create")
	public Timestamp getEavpDateCreate() {
		return this.eavpDateCreate;
	}

	public void setEavpDateCreate(Timestamp eavpDateCreate) {
		this.eavpDateCreate = eavpDateCreate;
	}


	@Column(name="eavp_date_update")
	public Timestamp getEavpDateUpdate() {
		return this.eavpDateUpdate;
	}

	public void setEavpDateUpdate(Timestamp eavpDateUpdate) {
		this.eavpDateUpdate = eavpDateUpdate;
	}


	@Column(name="eavp_establishment_number")
	public String getEavpEstablishmentNumber() {
		return this.eavpEstablishmentNumber;
	}

	public void setEavpEstablishmentNumber(String eavpEstablishmentNumber) {
		this.eavpEstablishmentNumber = eavpEstablishmentNumber;
	}


	@Column(name="eavp_forest_prod_mobil_guide")
	public String getEavpForestProdMobilGuide() {
		return this.eavpForestProdMobilGuide;
	}

	public void setEavpForestProdMobilGuide(String eavpForestProdMobilGuide) {
		this.eavpForestProdMobilGuide = eavpForestProdMobilGuide;
	}


	@Column(name="eavp_license_number")
	public String getEavpLicenseNumber() {
		return this.eavpLicenseNumber;
	}

	public void setEavpLicenseNumber(String eavpLicenseNumber) {
		this.eavpLicenseNumber = eavpLicenseNumber;
	}


	@Column(name="eavp_reexport_date")
	public Timestamp getEavpReexportDate() {
		return this.eavpReexportDate;
	}

	public void setEavpReexportDate(Timestamp eavpReexportDate) {
		this.eavpReexportDate = eavpReexportDate;
	}


	@Column(name="eavp_reexport_last_country")
	public String getEavpReexportLastCountry() {
		return this.eavpReexportLastCountry;
	}

	public void setEavpReexportLastCountry(String eavpReexportLastCountry) {
		this.eavpReexportLastCountry = eavpReexportLastCountry;
	}


	@Column(name="eavp_register_date")
	public Timestamp getEavpRegisterDate() {
		return this.eavpRegisterDate;
	}

	public void setEavpRegisterDate(Timestamp eavpRegisterDate) {
		this.eavpRegisterDate = eavpRegisterDate;
	}


	@Column(name="eavp_register_id")
	public String getEavpRegisterId() {
		return this.eavpRegisterId;
	}

	public void setEavpRegisterId(String eavpRegisterId) {
		this.eavpRegisterId = eavpRegisterId;
	}


	@Column(name="eavp_sample_amount")
	public BigDecimal getEavpSampleAmount() {
		return this.eavpSampleAmount;
	}

	public void setEavpSampleAmount(BigDecimal eavpSampleAmount) {
		this.eavpSampleAmount = eavpSampleAmount;
	}


	@Column(name="eavp_sample_amount_unit")
	public String getEavpSampleAmountUnit() {
		return this.eavpSampleAmountUnit;
	}

	public void setEavpSampleAmountUnit(String eavpSampleAmountUnit) {
		this.eavpSampleAmountUnit = eavpSampleAmountUnit;
	}


	@Column(name="eavp_sample_description")
	public String getEavpSampleDescription() {
		return this.eavpSampleDescription;
	}

	public void setEavpSampleDescription(String eavpSampleDescription) {
		this.eavpSampleDescription = eavpSampleDescription;
	}


	@Column(name="eavp_scientific_name_product")
	public String getEavpScientificNameProduct() {
		return this.eavpScientificNameProduct;
	}

	public void setEavpScientificNameProduct(String eavpScientificNameProduct) {
		this.eavpScientificNameProduct = eavpScientificNameProduct;
	}


	@Column(name="eavp_serial_product")
	public String getEavpSerialProduct() {
		return this.eavpSerialProduct;
	}

	public void setEavpSerialProduct(String eavpSerialProduct) {
		this.eavpSerialProduct = eavpSerialProduct;
	}


	@Column(name="eavp_species_code")
	public String getEavpSpeciesCode() {
		return this.eavpSpeciesCode;
	}

	public void setEavpSpeciesCode(String eavpSpeciesCode) {
		this.eavpSpeciesCode = eavpSpeciesCode;
	}


	@Column(name="eavp_species_name")
	public String getEavpSpeciesName() {
		return this.eavpSpeciesName;
	}

	public void setEavpSpeciesName(String eavpSpeciesName) {
		this.eavpSpeciesName = eavpSpeciesName;
	}


	@Column(name="eavp_status")
	public Boolean getEavpStatus() {
		return this.eavpStatus;
	}

	public void setEavpStatus(Boolean eavpStatus) {
		this.eavpStatus = eavpStatus;
	}


	@Column(name="eavp_tariff_subheading")
	public String getEavpTariffSubheading() {
		return this.eavpTariffSubheading;
	}

	public void setEavpTariffSubheading(String eavpTariffSubheading) {
		this.eavpTariffSubheading = eavpTariffSubheading;
	}


	@Column(name="eavp_tariff_subheading_descrip")
	public String getEavpTariffSubheadingDescrip() {
		return this.eavpTariffSubheadingDescrip;
	}

	public void setEavpTariffSubheadingDescrip(String eavpTariffSubheadingDescrip) {
		this.eavpTariffSubheadingDescrip = eavpTariffSubheadingDescrip;
	}


	@Column(name="eavp_total_amount_export")
	public BigDecimal getEavpTotalAmountExport() {
		return this.eavpTotalAmountExport;
	}

	public void setEavpTotalAmountExport(BigDecimal eavpTotalAmountExport) {
		this.eavpTotalAmountExport = eavpTotalAmountExport;
	}


	@Column(name="eavp_total_amount_export_unit")
	public String getEavpTotalAmountExportUnit() {
		return this.eavpTotalAmountExportUnit;
	}

	public void setEavpTotalAmountExportUnit(String eavpTotalAmountExportUnit) {
		this.eavpTotalAmountExportUnit = eavpTotalAmountExportUnit;
	}


	@Column(name="eavp_total_amount_quota")
	public BigDecimal getEavpTotalAmountQuota() {
		return this.eavpTotalAmountQuota;
	}

	public void setEavpTotalAmountQuota(BigDecimal eavpTotalAmountQuota) {
		this.eavpTotalAmountQuota = eavpTotalAmountQuota;
	}


	@Column(name="eavp_total_amount_quota_unit")
	public String getEavpTotalAmountQuotaUnit() {
		return this.eavpTotalAmountQuotaUnit;
	}

	public void setEavpTotalAmountQuotaUnit(String eavpTotalAmountQuotaUnit) {
		this.eavpTotalAmountQuotaUnit = eavpTotalAmountQuotaUnit;
	}


	@Column(name="eavp_update_date")
	public Timestamp getEavpUpdateDate() {
		return this.eavpUpdateDate;
	}

	public void setEavpUpdateDate(Timestamp eavpUpdateDate) {
		this.eavpUpdateDate = eavpUpdateDate;
	}


	@Column(name="eavp_updater_id")
	public String getEavpUpdaterId() {
		return this.eavpUpdaterId;
	}

	public void setEavpUpdaterId(String eavpUpdaterId) {
		this.eavpUpdaterId = eavpUpdaterId;
	}


	@Column(name="eavp_updater_ip")
	public String getEavpUpdaterIp() {
		return this.eavpUpdaterIp;
	}

	public void setEavpUpdaterIp(String eavpUpdaterIp) {
		this.eavpUpdaterIp = eavpUpdaterIp;
	}


	@Column(name="eavp_user_create")
	public String getEavpUserCreate() {
		return this.eavpUserCreate;
	}

	public void setEavpUserCreate(String eavpUserCreate) {
		this.eavpUserCreate = eavpUserCreate;
	}


	@Column(name="eavp_user_update")
	public String getEavpUserUpdate() {
		return this.eavpUserUpdate;
	}

	public void setEavpUserUpdate(String eavpUserUpdate) {
		this.eavpUserUpdate = eavpUserUpdate;
	}


	@Column(name="eavp_utilization")
	public String getEavpUtilization() {
		return this.eavpUtilization;
	}

	public void setEavpUtilization(String eavpUtilization) {
		this.eavpUtilization = eavpUtilization;
	}


	@Column(name="eavu_request_number")
	public String getEavuRequestNumber() {
		return this.eavuRequestNumber;
	}

	public void setEavuRequestNumber(String eavuRequestNumber) {
		this.eavuRequestNumber = eavuRequestNumber;
	}


	//bi-directional many-to-one association to ExportAuthorizationVue
	@ManyToOne
	@JoinColumn(name="eavu_id")
	public ExportAuthorizationVue getExportAuthorizationVue() {
		return this.exportAuthorizationVue;
	}

	public void setExportAuthorizationVue(ExportAuthorizationVue exportAuthorizationVue) {
		this.exportAuthorizationVue = exportAuthorizationVue;
	}

	@Transient
	public CommercialTermCites getDescriptionTerms() {
		return descriptionTerms;
	}

	@Transient
	public void setDescriptionTerms(CommercialTermCites descriptionTerms) {
		this.descriptionTerms = descriptionTerms;
	}

	@Transient
	public OriginCites getOriginCode() {
		return originCode;
	}

	@Transient
	public void setOriginCode(OriginCites originCode) {
		this.originCode = originCode;
	}

	@Transient
	public SpecieTaxa getSpecie() {
		return specie;
	}

	@Transient
	public void setSpecie(SpecieTaxa specie) {
		this.specie = specie;
	}

	@Transient
	public BigDecimal getAmount() {
		return amount;
	}

	@Transient
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@Column(name="eavp_description_terms_id")
	public Integer getEavpDescriptionTermsId() {
		return eavpDescriptionTermsId;
	}

	public void setEavpDescriptionTermsId(Integer eavpDescriptionTermsId) {
		this.eavpDescriptionTermsId = eavpDescriptionTermsId;
	}

	@Column(name="eavp_description_terms_catalog")
	public String getEavpDescriptionTermsCatalog() {
		return eavpDescriptionTermsCatalog;
	}

	public void setEavpDescriptionTermsCatalog(String eavpDescriptionTermsCatalog) {
		this.eavpDescriptionTermsCatalog = eavpDescriptionTermsCatalog;
	}

	@Column(name="eavp_origin_code_id")
	public Integer getEavpOriginCodeId() {
		return eavpOriginCodeId;
	}

	public void setEavpOriginCodeId(Integer eavpOriginCodeId) {
		this.eavpOriginCodeId = eavpOriginCodeId;
	}

	@Column(name="eavp_origin_code_id_catalog")
	public String getEavpOriginCodeIdCatalog() {
		return eavpOriginCodeIdCatalog;
	}

	public void setEavpOriginCodeIdCatalog(String eavpOriginCodeIdCatalog) {
		this.eavpOriginCodeIdCatalog = eavpOriginCodeIdCatalog;
	}

	@Column(name="eavp_quantity_unit_id")
	public Integer getEavpQuantityUnitId() {
		return eavpQuantityUnitId;
	}

	public void setEavpQuantityUnitId(Integer eavpQuantityUnitId) {
		this.eavpQuantityUnitId = eavpQuantityUnitId;
	}

	@Column(name="eavp_quantity_unit_catalog")
	public String getEavpQuantityUnitCatalog() {
		return eavpQuantityUnitCatalog;
	}

	public void setEavpQuantityUnitCatalog(String eavpQuantityUnitCatalog) {
		this.eavpQuantityUnitCatalog = eavpQuantityUnitCatalog;
	}

	@Column(name="eavp_specie_gui")
	public String getEavpSpecieGui() {
		return eavpSpecieGui;
	}

	public void setEavpSpecieGui(String eavpSpecieGui) {
		this.eavpSpecieGui = eavpSpecieGui;
	}

	@Transient
	public String getApendixOrigin() {
		return apendixOrigin;
	}

	@Transient
	public void setApendixOrigin(String apendixOrigin) {
		this.apendixOrigin = apendixOrigin;
	}

	@Transient
	public UnitMeasurement getUnitMeasurement() {
		return unitMeasurement;
	}

	@Transient
	public void setUnitMeasurement(UnitMeasurement unitMeasurement) {
		this.unitMeasurement = unitMeasurement;
	}

	@Transient
	public String getAmountText() {
		return amountText;
	}

	@Transient
	public void setAmountText(String amountText) {
		this.amountText = amountText;
	}

	@Transient
	public boolean isStatusApendix() {
		return statusApendix;
	}

	@Transient
	public void setStatusApendix(boolean statusApendix) {
		this.statusApendix = statusApendix;
	}
}