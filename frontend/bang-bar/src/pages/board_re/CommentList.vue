<template>
  <div>
    <div :key="item.comment_id" v-for="item in comments">
      <CommentListItem :commentObj="item"></CommentListItem>
    </div>
    <CommentCreate :contentId="contentId" :reloadComment="reloadComment"/>
  </div>
</template>

<script>
import data from "@/data";
import CommentListItem from "./CommentListItem";
import CommentCreate from "./CommentCreate";

export default {
  name: "CommentList",
  props: {
    contentId: Number
  },
  data() {
    return {
      comments: data.Comment.filter(commentItem => {
        return commentItem.content_id === this.contentId;
      })
    };
  },
  components: {
    CommentListItem,
    CommentCreate
  },
  methods: {
    reloadComment() {
      this.comments = data.Comment.filter(commentItem => {
        return commentItem.content_id === this.contentId;
      });
    }
  }
};
</script>

<style>
</style>