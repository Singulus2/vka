package com.devk.vka.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.BigDecimalFilter;

/**
 * Criteria class for the Vka entity. This class is used in VkaResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /vkas?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class VkaCriteria implements Serializable {

    private static final long serialVersionUID = 1L;


    private StringFilter mkt1;

    private StringFilter vnr;

    private StringFilter wirkungDatAb;

    private StringFilter wirkungDatBis;

    public StringFilter getWirkungDatAb() {
		return wirkungDatAb;
	}

	public void setWirkungDatAb(StringFilter wirkungDatAb) {
		this.wirkungDatAb = wirkungDatAb;
	}

	public StringFilter getWirkungDatBis() {
		return wirkungDatBis;
	}

	public void setWirkungDatBis(StringFilter wirkungDatBis) {
		this.wirkungDatBis = wirkungDatBis;
	}


    public StringFilter getMkt1() {
        return mkt1;
    }

    public void setMkt1(StringFilter mkt1) {
        this.mkt1 = mkt1;
    }


    public StringFilter getVnr() {
        return vnr;
    }

    public void setVnr(StringFilter vnr) {
        this.vnr = vnr;
    }


    @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VkaCriteria other = (VkaCriteria) obj;
		if (mkt1 == null) {
			if (other.mkt1 != null)
				return false;
		} else if (!mkt1.equals(other.mkt1))
			return false;
		if (vnr == null) {
			if (other.vnr != null)
				return false;
		} else if (!vnr.equals(other.vnr))
			return false;
		if (wirkungDatAb == null) {
			if (other.wirkungDatAb != null)
				return false;
		} else if (!wirkungDatAb.equals(other.wirkungDatAb))
			return false;
		if (wirkungDatBis == null) {
			if (other.wirkungDatBis != null)
				return false;
		} else if (!wirkungDatBis.equals(other.wirkungDatBis))
			return false;
		return true;
	}

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mkt1 == null) ? 0 : mkt1.hashCode());
		result = prime * result + ((vnr == null) ? 0 : vnr.hashCode());
		result = prime * result + ((wirkungDatAb == null) ? 0 : wirkungDatAb.hashCode());
		result = prime * result + ((wirkungDatBis == null) ? 0 : wirkungDatBis.hashCode());
		return result;
	}

    public String toString() {
    	return "Vermittlernr " + mkt1;
    }
}
