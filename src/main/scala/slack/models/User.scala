package slack.models

case class User (
  id: String,
  name: String,
  deleted: Option[Boolean] = None,
  color: Option[String] = None,
  profile: Option[UserProfile] = None,
  is_admin: Option[Boolean] = None,
  is_owner: Option[Boolean] = None,
  is_primary_owner: Option[Boolean] = None,
  is_restricted: Option[Boolean] = None,
  is_ultra_restricted: Option[Boolean] = None,
  has_2fa: Option[Boolean] = None,
  has_files: Option[Boolean] = None
)

case class UserProfile (
  first_name: Option[String],
  last_name: Option[String],
  real_name: Option[String],
  email: Option[String],
  skype: Option[String],
  phone: Option[String],
  image_24: String,
  image_32: String,
  image_48: String,
  image_72: String,
  image_192: String
)