<template>
  <div class="flex">
    
    <div class="flex flex-row flex-initial h-full w-full">
      <div class="flex-initial ">{{name}}</div>
      <textarea  placeholder="코멘트를 달아주세요." class="min-h-full min-w-full" ></textarea>
      <base-button></base-button>
      <button variant="info" @click="isSubComment ? createSubComment() : createComment()">작성하기</button>
    </div>
  </div>
</template>

<script>
import data from "@/data";
import BaseButton from '../../components/ui/BaseButton.vue';

export default {
  components: { BaseButton },
  name: "CommentCreate",
  props: {
    contentId: Number,
    reloadComment: Function,
    reloadSubComments: Function,
    subCommentToggle: Function,
    isSubComment: Boolean,
    commentId: Number
  },
  data() {
    return {
      name: "인성",
      context: ""
    };
  },
  methods: {
    createComment() {
      const comment_id = data.Comment[data.Comment.length - 1].comment_id + 1;
      data.Comment.push({
        comment_id: comment_id,
        user_id: 1,
        content_id: this.contentId,
        context: this.context,
        created_at: "2019-04-19",
        updated_at: null
      });
      this.reloadComment();
      // this.subCommentToggle();
      this.context = "";
    },
    createSubComment() {
      const subcomment_id =
        data.SubComment[data.SubComment.length - 1].subcomment_id + 1;
      data.SubComment.push({
        subcomment_id: subcomment_id,
        comment_id: this.commentId,
        user_id: 1,
        context: this.context,
        created_at: "2019-04-20",
        updated_at: null
      });
      this.reloadSubComments();
      this.subCommentToggle();
      this.context = "";
    }
  }
};
</script>

<style scoped>
.comment-create {
  display: flex;
  margin-bottom: 1em;
}
</style>