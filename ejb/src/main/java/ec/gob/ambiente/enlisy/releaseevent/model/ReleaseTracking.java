package ec.gob.ambiente.enlisy.releaseevent.model;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Where;

import java.sql.Timestamp;


/**
 * The persistent class for the release_trackings database table.
 * 
 */
@Entity
@Table(name="release_trackings", schema="biodiversity")
@NamedQuery(name="ReleaseTracking.findAll", query="SELECT r FROM ReleaseTracking r")
@Where(clause = "retr_status='t'")
public class ReleaseTracking implements Serializable {
	
	
	
	private static final long serialVersionUID = 1L;
	private Integer retrId;
	private double retrAltitude;
	private Timestamp retrDate;
	private Timestamp retrDateCreate;
	private Timestamp retrDateUpdate;
	private String retrDescription;
	private String retrDistanceSiteRelease;
	private String retrDocs;
	private String retrImg;
	private String retrNorthSouth;
	private String retrSignatory;
	private Boolean retrStatus = true;
	private String retrUserCreate;
	private String retrUserUpdate;
	private double retrX;
	private double retrY;
	private String x;
	private String y;
	private String retrZone;
	private ReleaseSpecy releaseSpecy;
	private int retrZoom;
	private ReleaseTrackingsDocument releaseImage = new ReleaseTrackingsDocument();
	private ReleaseTrackingsDocument releaseDocument = new ReleaseTrackingsDocument();

	public ReleaseTracking() {
	}


	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RELEASE_TRACKING_GENERATOR")
	@SequenceGenerator(name = "RELEASE_TRACKING_GENERATOR", initialValue = 1, sequenceName = "seq_retr_id", schema = "biodiversity", allocationSize = 1)
	@Column(name="retr_id")
	public Integer getRetrId() {
		return this.retrId;
	}

	public void setRetrId(Integer retrId) {
		this.retrId = retrId;
	}


	@Column(name="retr_altitude")
	public double getRetrAltitude() {
		return this.retrAltitude;
	}

	public void setRetrAltitude(double retrAltitude) {
		this.retrAltitude = retrAltitude;
	}


	@Column(name="retr_date")
	public Timestamp getRetrDate() {
		return this.retrDate;
	}

	public void setRetrDate(Timestamp retrDate) {
		this.retrDate = retrDate;
	}


	@Column(name="retr_date_create")
	public Timestamp getRetrDateCreate() {
		return this.retrDateCreate;
	}

	public void setRetrDateCreate(Timestamp retrDateCreate) {
		this.retrDateCreate = retrDateCreate;
	}


	@Column(name="retr_date_update")
	public Timestamp getRetrDateUpdate() {
		return this.retrDateUpdate;
	}

	public void setRetrDateUpdate(Timestamp retrDateUpdate) {
		this.retrDateUpdate = retrDateUpdate;
	}


	@Column(name="retr_description")
	public String getRetrDescription() {
		return this.retrDescription;
	}

	public void setRetrDescription(String retrDescription) {
		this.retrDescription = retrDescription;
	}


	@Column(name="retr_distance_site_release")
	public String getRetrDistanceSiteRelease() {
		return this.retrDistanceSiteRelease;
	}

	public void setRetrDistanceSiteRelease(String retrDistanceSiteRelease) {
		this.retrDistanceSiteRelease = retrDistanceSiteRelease;
	}


	@Column(name="retr_docs")
	public String getRetrDocs() {
		return this.retrDocs;
	}

	public void setRetrDocs(String retrDocs) {
		this.retrDocs = retrDocs;
	}


	@Column(name="retr_north_south")
	public String getRetrNorthSouth() {
		return this.retrNorthSouth;
	}

	public void setRetrNorthSouth(String retrNorthSouth) {
		this.retrNorthSouth = retrNorthSouth;
	}


	@Column(name="retr_signatory")
	public String getRetrSignatory() {
		return this.retrSignatory;
	}

	public void setRetrSignatory(String retrSignatory) {
		this.retrSignatory = retrSignatory;
	}


	@Column(name="retr_status")
	public Boolean getRetrStatus() {
		return this.retrStatus;
	}

	public void setRetrStatus(Boolean retrStatus) {
		this.retrStatus = retrStatus;
	}


	@Column(name="retr_user_create")
	public String getRetrUserCreate() {
		return this.retrUserCreate;
	}

	public void setRetrUserCreate(String retrUserCreate) {
		this.retrUserCreate = retrUserCreate;
	}


	@Column(name="retr_user_update")
	public String getRetrUserUpdate() {
		return this.retrUserUpdate;
	}

	public void setRetrUserUpdate(String retrUserUpdate) {
		this.retrUserUpdate = retrUserUpdate;
	}


	@Column(name="retr_x")
	public double getRetrX() {
		return this.retrX;
	}

	public void setRetrX(double retrX) {
		this.retrX = retrX;
	}


	@Column(name="retr_y")
	public double getRetrY() {
		return this.retrY;
	}

	public void setRetrY(double retrY) {
		this.retrY = retrY;
	}


	@Column(name="retr_zone")
	public String getRetrZone() {
		return this.retrZone;
	}

	public void setRetrZone(String retrZone) {
		this.retrZone = retrZone;
	}
	
	@Column(name="retr_zoom")
	public int getRetrZoom() {
		return retrZoom;
	}

	public void setRetrZoom(int retrZoom) {
		this.retrZoom = retrZoom;
	}
	
	@Column(name="retr_img")
	public String getRetrImg() {
		return retrImg;
	}

	public void setRetrImg(String retrImg) {
		this.retrImg = retrImg;
	}


	//bi-directional many-to-one association to ReleaseSpecy
	@ManyToOne
	@JoinColumn(name="resp_id")
	public ReleaseSpecy getReleaseSpecy() {
		return this.releaseSpecy;
	}

	public void setReleaseSpecy(ReleaseSpecy releaseSpecy) {
		this.releaseSpecy = releaseSpecy;
	}

	@Transient
	public String getX() {
		return x;
	}


	public void setX(String x) {
		this.x = x;
	}

	@Transient
	public String getY() {
		return y;
	}


	public void setY(String y) {
		this.y = y;
	}

	@Transient
	public ReleaseTrackingsDocument getReleaseImage() {
		return releaseImage;
	}


	public void setReleaseImage(ReleaseTrackingsDocument releaseImage) {
		this.releaseImage = releaseImage;
	}

	@Transient
	public ReleaseTrackingsDocument getReleaseDocument() {
		return releaseDocument;
	}


	public void setReleaseDocument(ReleaseTrackingsDocument releaseDocument) {
		this.releaseDocument = releaseDocument;
	}	
}