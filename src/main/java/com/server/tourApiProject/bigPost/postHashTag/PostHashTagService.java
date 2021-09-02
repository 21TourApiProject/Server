package com.server.tourApiProject.bigPost.postHashTag;

import com.server.tourApiProject.bigPost.postImage.PostImage;
import com.server.tourApiProject.hashTag.HashTag;
import com.server.tourApiProject.hashTag.HashTagRepository;
import com.server.tourApiProject.bigPost.post.Post;
import com.server.tourApiProject.bigPost.post.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PostHashTagService {
    private final PostHashTagRepository postHashTagRepository;
    private final PostRepository postRepository;
    private final HashTagRepository hashTagRepository;


    public List<PostHashTag> getPostHashTag(Long postId) {
        return postHashTagRepository.findByPostId(postId);
    }

    public List<String> getPostHashTagName(Long postId) {
        List<String> postHashTagNameList =new ArrayList<>();
        List<PostHashTag> postHashTagList = postHashTagRepository.findByPostId(postId);
        for(PostHashTag p : postHashTagList) {
            postHashTagNameList.add(p.getHashTagName());
        }
        return postHashTagNameList;
    }

    public void createPostHashTags(Long postId,List<PostHashTagParams> postHashTagParams) {
        for (PostHashTagParams p : postHashTagParams) {
            Post post = postRepository.findById(postId).orElseThrow(IllegalAccessError::new);
            PostHashTag postHashTag = new PostHashTag();
            postHashTag.setHashTagName(p.getHashTagName());
            postHashTag.setPost(post);
            postHashTag.setPostId(post.getPostId());
            HashTag hashTag = hashTagRepository.findByHashTagName(p.getHashTagName());
            postHashTag.setHashTagId(hashTag.getHashTagId());
            postHashTagRepository.save(postHashTag);
        }
    }
}
