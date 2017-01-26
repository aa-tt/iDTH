package entities;

import java.io.Serializable;

public class AdditionalChannelId implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer cust; // variable names here should match persistent variable name in entity class
	private Integer chan;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((chan == null) ? 0 : chan.hashCode());
		result = prime * result + ((cust == null) ? 0 : cust.hashCode());
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
		AdditionalChannelId other = (AdditionalChannelId) obj;
		if (chan == null) {
			if (other.chan != null)
				return false;
		} else if (!chan.equals(other.chan))
			return false;
		if (cust == null) {
			if (other.cust != null)
				return false;
		} else if (!cust.equals(other.cust))
			return false;
		return true;
	}
}
