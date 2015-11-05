package slack.models

import play.api.libs.json._

// TODO: Revisit all event objects (some are partial? - specifically channel)
sealed trait SlackEvent

sealed trait ChannelName {
  def channel: String
}

object ChannelName {
  def unapply(a: Any) = a match {
    case c: ChannelName => Some(c.channel)
    case _ => None
  }
}

sealed trait UserName {
  def user: String
}

object UserName {
  def unapply(a: Any) = a match {
    case u: UserName => Some(u.user)
    case _ => None
  }
}

case class Hello (
  `type`: String
) extends SlackEvent

// TODO: Message Sub-types
case class Message (
  ts: String,
  channel: String,
  user: String,
  text: String,
  is_starred: Option[Boolean]
) extends SlackEvent with ChannelName with UserName

case class SubMessage (
  ts: String,
  user: String,
  text: String,
  is_starred: Option[Boolean]
) extends SlackEvent with UserName

// TODO: Message Sub-types
case class MessageWithSubtype (
  ts: String,
  message: Option[SubMessage],
  subtype: String,
  hidden: Option[Boolean],
  event_ts: Option[String],
  channel: String
) extends SlackEvent with ChannelName

case class ReactionAdded (
  reaction: String,
  item: JsValue, // TODO: Different item types -- https://api.slack.com/methods/stars.list
  event_ts: String,
  user: String
) extends SlackEvent with UserName

case class ReactionRemoved (
  reaction: String,
  item: JsValue, // TODO: Different item types -- https://api.slack.com/methods/stars.list
  event_ts: String,
  user: String
) extends SlackEvent with UserName

case class UserTyping (
  channel: String,
  user: String
) extends SlackEvent with ChannelName with UserName

case class ChannelMarked (
  channel: String,
  ts: String
) extends SlackEvent with ChannelName

case class ChannelCreated (
  channel: Channel
) extends SlackEvent

case class ChannelJoined (
  channel: Channel
) extends SlackEvent

case class ChannelLeft (
  channel: String
) extends SlackEvent with ChannelName

case class ChannelDeleted (
  channel: String
) extends SlackEvent with ChannelName

case class ChannelRename (
  channel: Channel
) extends SlackEvent

case class ChannelArchive (
  channel: String,
  user: String
) extends SlackEvent with ChannelName with UserName

case class ChannelUnarchive (
  channel: String,
  user: String
) extends SlackEvent with ChannelName with UserName

case class ChannelHistoryChanged (
  latest: Long,
  ts: String,
  event_ts: String
) extends SlackEvent

case class ImCreated (
  user: String,
  channel: Im
) extends SlackEvent

case class ImOpened (
  user: String,
  channel: String
) extends SlackEvent with ChannelName with UserName

case class ImClose (
  user: String,
  channel: String
) extends SlackEvent with ChannelName with UserName

case class ImMarked (
  channel: String,
  ts: String
) extends SlackEvent with ChannelName

case class ImHistoryChanged (
  latest: Long,
  ts: String,
  event_ts: String
) extends SlackEvent

case class GroupJoined(
  channel: Channel
) extends SlackEvent

case class GroupLeft (
  channel: String
) extends SlackEvent with ChannelName

case class GroupOpen (
  user: String,
  channel: String
) extends SlackEvent with ChannelName with UserName

case class GroupClose (
  user: String,
  channel: String
) extends SlackEvent with ChannelName with UserName

case class GroupArchive (
  channel: String
) extends SlackEvent with ChannelName

case class GroupUnarchive (
  channel: String
) extends SlackEvent with ChannelName

case class GroupRename (
  channel: Channel
) extends SlackEvent

case class GroupMarked (
  channel: String,
  ts: String
) extends SlackEvent with ChannelName

case class GroupHistoryChanged (
  latest: Long,
  ts: String,
  event_ts: String
) extends SlackEvent

case class FileCreated (
  file: SlackFile
) extends SlackEvent

case class FileShared (
  file: SlackFile
) extends SlackEvent

case class FileUnshared (
  file: SlackFile
) extends SlackEvent

case class FilePublic (
  file: SlackFile
) extends SlackEvent

case class FilePrivate (
  file: String
) extends SlackEvent

case class FileChange (
  file: SlackFile
) extends SlackEvent

case class FileDeleted (
  file_id: String,
  event_ts: String
) extends SlackEvent

case class FileCommentAdded (
  file: SlackFile,
  comment: JsValue // TODO: SlackComment?
) extends SlackEvent

case class FileCommentEdited (
  file: SlackFile,
  comment: JsValue // TODO: SlackComment?
) extends SlackEvent

case class FileCommentDeleted (
  file: SlackFile,
  comment: String
) extends SlackEvent


// Format of event is tbd
case class PinAdded (
  `type`: String
) extends SlackEvent

// Format of event is tbd
case class PinRemoved (
  `type`: String
) extends SlackEvent

case class PresenceChange (
  user: String,
  presence: String
) extends SlackEvent with UserName

case class ManualPresenceChange (
  presence: String
) extends SlackEvent

case class PrefChange (
  name: String,
  value: String
) extends SlackEvent

case class UserChange (
  user: User
) extends SlackEvent

case class TeamJoin (
  user: User
) extends SlackEvent

case class StarAdded (
  user: String,
  item: JsValue, // TODO: Different item types -- https://api.slack.com/methods/stars.list
  event_ts: String
) extends SlackEvent with UserName

case class StarRemoved (
  user: String,
  item: JsValue, // TODO: Different item types -- https://api.slack.com/methods/stars.list
  event_ts: String
) extends SlackEvent with UserName

case class EmojiChanged (
  event_ts: String
) extends SlackEvent

case class CommandsChanged (
  event_ts: String
) extends SlackEvent

case class TeamPlanChanged (
  plan: String
) extends SlackEvent

case class TeamPrefChanged (
  name: String,
  value: String // TODO: Primitive type?
) extends SlackEvent

case class TeamRename (
  name: String
) extends SlackEvent

case class TeamDomainChange (
  url: String,
  domain: String
) extends SlackEvent

case class BotAdded (
  bot: JsValue // TODO: structure -- https://api.slack.com/events/bot_added
) extends SlackEvent

case class BotChanged (
  bot: JsValue // TODO: structure -- https://api.slack.com/events/bot_added
) extends SlackEvent

case class AccountsChanged (
  `type`: String
) extends SlackEvent

case class TeamMigrationStarted (
  `type`: String
) extends SlackEvent