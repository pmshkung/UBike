package ubike;

import java.io.Serializable;

/**
 *
 * @author Webb
 */
public class UBikeData implements Serializable {        
    public String mCountryCode = "";
    public String mAreaCode = "";
    public String mType = "";
    public String mStatus = "";
    public String mStationNo = "";
    public String mNameTw = "";
    public String mDistrictTw = "";
    public String mAddressTw = "";
    public String mNameEn = "";
    public String mDistrictEn = "";
    public String mAddressEn = "";
    public String mNameCn = "";
    public String mDistrictCn = "";
    public String mAddressCn = "";
    public String mParkingSpaces = "";
    public String mAvailableSpaces = "";
    public String mEmptySpaces = "";
    public String mForbiddenSpaces = "";
    public String mLat = "";
    public String mLng = "";
    public String mImg = "";
    public String mUpdatedAt = "";
    public String mTime = "";   
    
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        
        result.append(UBikeJson.UBikeStop[0]); result.append(":");result.append(this.mCountryCode); result.append("\n");
        result.append(UBikeJson.UBikeStop[1]); result.append(":");result.append(this.mAreaCode); result.append("\n");
        result.append(UBikeJson.UBikeStop[2]); result.append(":");result.append(this.mType); result.append("\n");
        result.append(UBikeJson.UBikeStop[3]); result.append(":");result.append(this.mStatus); result.append("\n");
        result.append(UBikeJson.UBikeStop[4]); result.append(":");result.append(this.mStationNo); result.append("\n");
        result.append(UBikeJson.UBikeStop[5]); result.append(":");result.append(this.mNameTw); result.append("\n");
        result.append(UBikeJson.UBikeStop[6]); result.append(":");result.append(this.mDistrictTw); result.append("\n");
        result.append(UBikeJson.UBikeStop[7]); result.append(":");result.append(this.mAddressTw); result.append("\n");
        result.append(UBikeJson.UBikeStop[8]); result.append(":");result.append(this.mNameEn); result.append("\n");
        result.append(UBikeJson.UBikeStop[9]); result.append(":");result.append(this.mDistrictEn); result.append("\n");
        result.append(UBikeJson.UBikeStop[10]); result.append(":");result.append(this.mAddressEn); result.append("\n");
        result.append(UBikeJson.UBikeStop[11]); result.append(":");result.append(this.mNameCn); result.append("\n");
        result.append(UBikeJson.UBikeStop[12]); result.append(":");result.append(this.mDistrictCn); result.append("\n");        
        result.append(UBikeJson.UBikeStop[13]); result.append(":");result.append(this.mAddressCn); result.append("\n");
        result.append(UBikeJson.UBikeStop[14]); result.append(":");result.append(this.mParkingSpaces); result.append("\n");
        result.append(UBikeJson.UBikeStop[15]); result.append(":");result.append(this.mAvailableSpaces); result.append("\n");
        result.append(UBikeJson.UBikeStop[16]); result.append(":");result.append(this.mEmptySpaces); result.append("\n");
        result.append(UBikeJson.UBikeStop[17]); result.append(":");result.append(this.mForbiddenSpaces); result.append("\n");
        result.append(UBikeJson.UBikeStop[18]); result.append(":");result.append(this.mLat); result.append("\n");
        result.append(UBikeJson.UBikeStop[19]); result.append(":");result.append(this.mLng); result.append("\n");
        result.append(UBikeJson.UBikeStop[20]); result.append(":");result.append(this.mImg); result.append("\n");
        result.append(UBikeJson.UBikeStop[21]); result.append(":");result.append(this.mUpdatedAt); result.append("\n");
        result.append(UBikeJson.UBikeStop[22]); result.append(":");result.append(this.mTime); result.append("\n");
        
        return result.toString();   
    }
}
