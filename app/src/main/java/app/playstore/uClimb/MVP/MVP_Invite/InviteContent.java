package app.playstore.uClimb.MVP.MVP_Invite;

import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class InviteContent {
    /**
     * The content of an invitation, with optional fields to accommodate all presenters.
     * This type could be modified to also include an image, for sending invites over email.
     */

        /**
         * The subject of the message. Not used for invites without subjects, like SMS.
         **/
        @Nullable
        public String subject;

        /**
         * The body of the message. Indispensable content should go here.
         **/
        @Nullable
        public String body;

        /**
         * The URL containing the link to invite. In link-copy cases, only this field will be used.
         **/
        @NonNull
        public Uri link;

        public InviteContent(@Nullable String subject, @Nullable String body, @NonNull Uri link) {
            this.body = body;
            this.subject = subject;
            this.link = link;
            // ...
        }


}
