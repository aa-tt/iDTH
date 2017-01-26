package entities;

import java.io.Serializable;
import java.util.Random;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name="dth.channel")
public class Channel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "channel_seq")
	@SequenceGenerator(name = "channel_seq", sequenceName = "channel_seq", initialValue = 1, allocationSize = 1)
	@Id @Column(name="channelno")
	private Integer channelNo;
	@Column(name="channelname")
	private String channelName;
	/*@Column(name="package_id")*/
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="packageid")
	@JsonIgnore
	private Packag packag;
	
	@OneToMany(/*fetch = FetchType.EAGER, */
			targetEntity=entities.AdditionalChannel.class, 
			mappedBy = "chan", 
			cascade=CascadeType.ALL)
	@JsonIgnore
    private Set<AdditionalChannel> additionalChannels;
	
	public Channel() {}
	public Channel(Packag packag) {
		this.setPackag(packag);
		this.setChannelName("Channel-" + String.valueOf(new Random().nextInt(299)));
	}
	
	public Integer getChannelNo() {
		return channelNo;
	}
	public void setChannelNo(Integer channelNo) {
		this.channelNo = channelNo;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public Packag getPackag() {
		return packag;
	}
	public void setPackag(Packag packag) {
		this.packag = packag;
	}
	
	public Set<AdditionalChannel> getAdditionalChannels() {
		return additionalChannels;
	}
	public void setAdditionalChannels(Set<AdditionalChannel> additionalChannels) {
		this.additionalChannels = additionalChannels;
	}
	@Override
	public String toString() {
		return "Channel [channelNo=" + channelNo + ", channelName=" + channelName + ", packagId=" + packag.getPackageId() + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((channelNo == null) ? 0 : channelNo.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Channel other = (Channel) obj;
		if (channelNo == null && channelName == null) {
			if (other.channelNo != null && other.channelName != null)
				return false;
		} else if (!(/*channelNo.equals(other.channelNo) && */channelName.equals(other.channelName)))
			return false;
		return true;
	}
}
