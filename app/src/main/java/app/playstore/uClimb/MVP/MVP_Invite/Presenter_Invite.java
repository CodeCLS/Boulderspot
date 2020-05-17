package app.playstore.uClimb.MVP.MVP_Invite;

import android.content.Context;

import androidx.annotation.DrawableRes;

public class Presenter_Invite {
    public String id;
    /**
     * Presents the invite using a specific method, such as email or social.
     */

        /**
         * The user-visible name of the invite method, like 'Email' or 'SMS'
         **/
        public String name;

        /**
         * An icon representing the invite method.
         **/
        @DrawableRes
        public int icon;

        /**
         * Whether or not the method is available on this device. For example, SMS is phone only.
         **/
        public  boolean isAvailable;

        /**
         * The Content of the invitation
         **/
        public  InviteContent content;

        public Presenter_Invite(String id, @DrawableRes int icon, boolean isAvailable, InviteContent content) {
            this.id = id;
            // ...
        }

        /**
         * Send the invitation using the specified method.
         */
        public void sendInvite(Context context) {
            // ...
        }





}
